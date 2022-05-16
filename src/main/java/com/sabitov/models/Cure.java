package com.sabitov.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "ills")
public class Cure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "cure_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private List<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "cure_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ill_id", referencedColumnName = "id"))
    private List<Ill> ills;

}



