package com.miu.se.user;

import com.miu.se.common.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddrRepository extends JpaRepository<ShippingAddress, Long> {
}