package com.nisum.evaluacionJava.model.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements Serializable {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;


    @Column(name = "email ", unique = true)
    private String email;

    @Column(name = "password ")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Phone> phones;

    @Temporal(TemporalType.DATE)
    @Column(name = "created ")
    private Date created;

    @Temporal(TemporalType.DATE)
    @Column(name = "modified ")
    private Date modified;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_login ")
    private Date lastLogin;

    @Column(name = "active ")
    private Integer active;

    @Column(name = "Bearer_Token")
    private String bearer;

}
