package com.miu.se.user;

import com.miu.se.common.entity.CartItem;
import com.miu.se.common.entity.Role;
import com.miu.se.common.entity.ShippingAddress;
import com.miu.se.common.entity.User;
import com.miu.se.common.entity.UserRole;
import com.miu.se.common.interfacing.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * @author XIII
 */
@Service
@Transactional
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ShippingAddrRepository shippingAddrRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public User getUserById(final Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(final User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void addRoles(Long userId, Role role) {
        User user = userRepository.getOne(userId);
        UserRole userRole = new UserRole(user.getId(), role);
        if(user.getRoles() == null) {
            user.setUserRoles(Arrays.asList(userRole));
        } else {
            user.getUserRoles().add(userRole);
        }
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User createIfNotExist(User user) {
        User local = userRepository.findOneByEmail(user.getEmail());
        if (local == null) {
            local = userRepository.save(user);
        }
        return local;
    }

    public ShippingAddress getShippingAddrById(final Long id) {
        return shippingAddrRepository.findById(id).orElse(null);
    }

    @Override
    public void editShippingAddr(Long userId, Long shippingAddrId, ShippingAddress editedShippingAddress) {
        User user = userRepository.getOne(userId);

        List<ShippingAddress> listShippingAddr = user.getAddresses();
        for (int i = 0; i < listShippingAddr.size(); i++) {
            if (listShippingAddr.get(i).getId().equals(shippingAddrId)) {
                listShippingAddr.get(i).setStreet(editedShippingAddress.getStreet());
                listShippingAddr.get(i).setCity(editedShippingAddress.getCity());
                listShippingAddr.get(i).setState(editedShippingAddress.getState());
                listShippingAddr.get(i).setZipCode(editedShippingAddress.getZipCode());
            }
            break;
        }
        userRepository.save(user);
    }

    @Override
    public boolean checkExists(String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) return true;
        else return false;
    }

    @Override
    public void clearCartItems(Long userId) {
        User user = userRepository.getOne(userId);
        List<CartItem> cartItems = user.getCartItems();
        for (CartItem item: cartItems) {
            cartItemRepository.delete(item);
        }
    }
}
