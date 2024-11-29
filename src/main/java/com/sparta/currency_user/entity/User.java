package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany( mappedBy = "user_id", cascade = CascadeType.REMOVE )
    private List<RequestExchange> requestExchangeList = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

}