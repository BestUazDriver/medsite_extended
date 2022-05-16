package com.sabitov.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "cures")
public class Ill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "ills")
    private List<Cure> cures;

    @ManyToMany(mappedBy = "illness")
    private List<Account> accounts;

}
