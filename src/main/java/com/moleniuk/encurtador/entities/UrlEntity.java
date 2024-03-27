package com.moleniuk.encurtador.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TABLE_URL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LONG_URL")
    private String longUrl;

    @Column(name = "SHORT_URL")
    private String shortUrl;

    @Column(name = "HITS")
    private Long hits;

    @Column(name = "CREATED_AT")
    private String createdAt;

}
