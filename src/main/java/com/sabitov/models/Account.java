package com.sabitov.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
@ToString(exclude = {"illness", "notes", "docNotes", "avatar"})
@EqualsAndHashCode(exclude = {"illness", "notes", "docNotes", "avatar"})
public class Account {

    public enum Role {
        USER, ADMIN, DOCTOR
    }

    public enum State {
        CONFIRMED, NOT_CONFIRMED, DELETED, BANNED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Column(name = "confirm_code")
    private String confirmCode;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ill_id", referencedColumnName = "id"))
    private List<Ill> illness;

    @OneToOne
    private Avatar avatar;

    @OneToMany(mappedBy = "patient")
    private List<Note> notes;

    @OneToMany(mappedBy = "doctor")
    private List<Note> docNotes;

}

