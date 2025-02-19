package com.cars24.csms.data.entities;

import lombok.Data;
import jakarta.persistence.*;

@Table(name = "customers")
@Entity
@Data
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID for new customers
    @Column(name = "customer_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String address;

    @Column(name = "usertype")
    private String usertype;

    @Column(name = "user_id")
    private String user_id;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
//    private AppUserEntity appUser;

    @Column(name = "is_deleted")
    private boolean is_deleted;
}

