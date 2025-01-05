package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(mappedBy = "addedBy")
//    @JoinColumn(name = "added_word", unique = true)
    private Set<Word> addedWord;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Word> getAddedWord() {
        return addedWord;
    }

    public void setAddedWord(Set<Word> addedWord) {
        this.addedWord = addedWord;
    }
}
