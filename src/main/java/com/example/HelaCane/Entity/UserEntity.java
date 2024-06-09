package com.example.HelaCane.Entity;

import com.example.HelaCane.Constant.CommonStatus;
import com.example.HelaCane.Constant.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userName;

    @Column
    private String email;

    @Column
    private String tel;

    @Column
    private String regDate;

    @Column
    private String password;

    @Enumerated
    private Role role;

    @Enumerated
    private CommonStatus commonStatus;
}
