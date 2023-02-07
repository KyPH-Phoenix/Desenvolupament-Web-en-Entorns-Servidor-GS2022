package com.villanueva.demohibernate.models;

import jakarta.persistence.*;

@Entity
@Table(
            name = "actor_film",
            uniqueConstraints = @UniqueConstraint(columnNames = {"actor_id", "film_id"})
)
public class ActorFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    Actor actor;

    @ManyToOne
    @JoinColumn(name = "film_id")
    Film film;

    String cast;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
