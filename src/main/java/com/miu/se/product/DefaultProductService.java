package com.miu.se.product;

import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.Review;
import com.miu.se.common.entity.User;
import com.miu.se.common.interfacing.ProductService;
import com.miu.se.common.interfacing.ReviewService;
import com.miu.se.common.interfacing.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class DefaultProductService implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Review addReview(Long id, Review review) {
        User user = userService.getUserById(0L);
        Product product = productRepository.getOne(id);
        review.setUser(user);
        Review savedReview = reviewService.save(review);
        if(product.getReviews() == null) {
            product.setReviews(Arrays.asList(savedReview));
        } else {
            product.getReviews().add(savedReview);
        }
        productRepository.save(product);
        return savedReview;
    }

}
