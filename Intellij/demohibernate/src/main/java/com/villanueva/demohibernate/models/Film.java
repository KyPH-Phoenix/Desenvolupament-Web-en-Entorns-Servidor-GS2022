package com.villanueva.demohibernate.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    @OneToMany(mappedBy = "film")
    Set<ActorFilm> actorFilms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<ActorFilm> getActorFilms() {
        return actorFilms;
    }

    public void setActorFilms(Set<ActorFilm> actorFilms) {
        this.actorFilms = actorFilms;
    }
}
