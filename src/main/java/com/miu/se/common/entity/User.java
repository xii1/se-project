package com.miu.se.common.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.miu.se.common.dto.GetUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author XIII
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "nickname", length = 50, nullable = true)
    private String nickname;

    @Column(name = "bio", length = 200, nullable = true)
    private String bio;

    @Column(name = "gender", length = 10, nullable = true)
    private String gender;

    @Column(name = "dob", length = 20, nullable = true)
    private String dob;

    @Column(name = "phone_number", length = 20, nullable = true)
    private String phoneNumber;

//    @JsonIgnore
//    @Override
//    public String getUsername() {
//        return email;
//    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<UserRole> userRoles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private List<Review> reviews;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<ShippingAddress> addresses;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<CartItem> cartItems;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private List<Order> orders;

    public User(Long Id) {
        this.id = Id;
    }

    public Set<Role> getRoles() {
        if(userRoles != null) {
            return userRoles.stream().map(UserRole::getRole).collect(Collectors.toSet());
        }
        return null;
    }

    public void setRoles(Set<Role> roles) {
        if(roles != null) {
            userRoles = roles.stream().map(role -> new UserRole(id, role)).collect(Collectors.toList());
        }
    }

//    @JsonIgnore
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for(Role role: getRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getValue()));
//        }
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", nickname='" + nickname + '\'' +
                ", bio='" + bio + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userRoles=" + userRoles +
                ", reviews=" + reviews +
                ", addresses=" + addresses +
                ", cartItems=" + cartItems +
                '}';
    }

    public GetUserDTO toGetUserDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, GetUserDTO.class);
    }
}
