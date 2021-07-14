package com.miu.se.common.entity;

import com.miu.se.common.dto.GetShopDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private List<Product> products;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    public Shop(Long id) {
        this.Id = id;
    }

    public GetShopDTO toGetShopDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, GetShopDTO.class);
    }
}
