package com.plb.vinylmgt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "vinyl")
public class Vinyl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " vinylSequenceGenerator")
    @SequenceGenerator(name = "vinylSequenceGenerator", allocationSize = 1)
    private Long id;

    @Column(name = "song_name", nullable = false)
    private String songName;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @JsonIgnoreProperties("vinyls")
    @ManyToOne
    private Author author;

    @JsonIgnoreProperties("vinyls")
    @ManyToOne
    private User user;

    public Vinyl() {
    }

    public Vinyl(String songName, LocalDate releaseDate) {
        this.songName = songName;
        this.releaseDate = releaseDate;
    }

    public Vinyl(String songName, LocalDate releaseDate, Author author, User user) {
        this.songName = songName;
        this.releaseDate = releaseDate;
        this.author = author;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vinyl vinyl = (Vinyl) o;
        return Objects.equals(songName, vinyl.songName) && Objects.equals(releaseDate, vinyl.releaseDate) && Objects.equals(author, vinyl.author) && Objects.equals(user, vinyl.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songName, releaseDate, author, user);
    }
}
