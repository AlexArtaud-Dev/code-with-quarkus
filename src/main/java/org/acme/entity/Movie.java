package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

// Ce fichier Entity permet de définir l'entité Movie qui servira dans le cadre de notre API.
@Entity
public class Movie extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public int copies;

    @Column(nullable = false, name = "movie_type")
    public String movieType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_actor_id")
    public Actor mainActor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_sec_actors",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_actor"))
    public Set<Actor> secondaryActors = new HashSet<>();
}

////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////      DA52 - EXAMEN FINAL           ////////////////////////////////
//////////////////////      ARTAUD ALEXANDRE - BAR VALENTIN         ////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
