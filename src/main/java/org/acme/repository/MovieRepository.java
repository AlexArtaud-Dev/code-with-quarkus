package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Movie;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {}

////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////      DA52 - EXAMEN FINAL           ////////////////////////////////
//////////////////////      ARTAUD ALEXANDRE - BAR VALENTIN         ////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////