package com.cars24.csms.data.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user_details")
@Entity
@Data
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "user_id" )
    private int user_id;

    @Column( name = "username")
    private String username;

    @Column( name = "password")
    private String password;

    @Column( name = "isActive" )
    private boolean isActive;



}
