package com.plb.vinylmgt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "author")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " authorSequenceGenerator")
    @SequenceGenerator(name = "authorSequenceGenerator", allocationSize = 1)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("author")
    private Set<Vinyl> vinyls = new HashSet<>();

    public Author() {
    }

    public Author(String firstname, String name, LocalDate birthDate) {
        this.firstname = firstname;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Author(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Vinyl> getVinyls() {
        return vinyls;
    }

    public void setVinyls(Set<Vinyl> vinyls) {
        this.vinyls = vinyls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(firstname, author.firstname) && Objects.equals(name, author.name) && Objects.equals(birthDate, author.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, name, birthDate);
    }
}
