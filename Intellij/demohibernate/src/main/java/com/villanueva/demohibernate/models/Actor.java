package com.villanueva.demohibernate.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToMany(mappedBy = "actor")
    Set<ActorFilm> actorFilms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ActorFilm> getActorFilms() {
        return actorFilms;
    }

    public void setActorFilms(Set<ActorFilm> actorFilms) {
        this.actorFilms = actorFilms;
    }
}
