package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UserDTO",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "nickname")
    }
)
@Getter
@Setter
@NoArgsConstructor
public class User {


    public User(String email, String password, String link, String nickname){
        this.email = email;
        this.password = password;
        this.link = link;
        this.nickname = nickname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "link")
    private String link;

    @Column(name = "nickname")
    private String nickname;

}
