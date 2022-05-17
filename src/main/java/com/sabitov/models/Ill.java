package com.sabitov.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"cures", "accounts"})
@EqualsAndHashCode(exclude = {"cures", "accounts"})
public class Ill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "ills")
    private List<Cure> cures;

    @JsonBackReference
    @ManyToMany(mappedBy = "illness")
    private List<Account> accounts;

}
