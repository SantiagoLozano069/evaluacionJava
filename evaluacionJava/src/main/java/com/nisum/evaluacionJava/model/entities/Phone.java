package com.nisum.evaluacionJava.model.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "phones")
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "cityCode")
    private String cityCode;

    @Column(name = "contryCode")
    private String countryCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user", nullable = false, updatable = false)
    private User user;
}
