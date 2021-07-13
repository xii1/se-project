package com.miu.se.common.entity;

import lombok.Getter;
import lombok.Setter;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private Date createdAt;

    @Column
    private Long specialUserId;

    @Column
    private Date updatedAt;

    @Column
    private String note;

    @Column
    private boolean countedPoint = false;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "order")
    private List<OrderItem> items;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "shipping_adddress_id")
//    private ShippingAddress shippingAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public double calculateTotalCost() {
        return items.stream().mapToDouble(OrderItem::calculateCost).sum();
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", note='" + note + '\'' +
                ", countedPoint=" + countedPoint +
                ", items=" + items +
                ", transaction=" + transaction +
                '}';
    }
}
