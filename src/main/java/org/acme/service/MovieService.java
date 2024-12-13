package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import org.acme.entity.Actor;
import org.acme.entity.Movie;
import org.acme.repository.ActorRepository;
import org.acme.repository.MovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

// Ce fichier Service permet de fournir les méthodes pour manipuler les films.
@ApplicationScoped
public class MovieService {

    @Inject
    MovieRepository movieRepository;

    @Inject
    ActorRepository actorRepository;

    public List<Movie> listAllMovies() {
        return movieRepository.listAll();
    }

    public Optional<Movie> findMovieById(Long id) {
        return movieRepository.findByIdOptional(id);
    }

    @Transactional
    public Movie createMovie(String title, int copies, String movieType, Long mainActorId) {
        Movie movie = new Movie();
        movie.title = title;
        movie.copies = copies;
        movie.movieType = movieType;

        if (mainActorId != null) {
            Actor mainActor = actorRepository.findById(mainActorId);
            if (mainActor == null) {
                throw new WebApplicationException("Acteur principal non existant", 400);
            }
            movie.mainActor = mainActor;
        }

        movieRepository.persist(movie);
        return movie;
    }

    @Transactional
    public boolean deleteMovie(Long id) {
        return movieRepository.deleteById(id);
    }

    public Set<Actor> listSecondaryActors(Long movieId) {
        Optional<Movie> optMovie = findMovieById(movieId);
        return optMovie.map(m -> m.secondaryActors).orElse(Set.of());
    }

    @Transactional
    public boolean addSecondaryActor(Long movieId, Long actorId) {
        Movie movie = movieRepository.findById(movieId);
        if (movie == null) {
            return false;
        }
        Actor actor = actorRepository.findById(actorId);
        if (actor == null) {
            return false;
        }
        movie.secondaryActors.add(actor);
        return true;
    }

    @Transactional
    public boolean removeSecondaryActor(Long movieId, Long actorId) {
        Movie movie = movieRepository.findById(movieId);
        if (movie == null) {
            return false;
        }
        Actor actor = actorRepository.findById(actorId);
        if (actor == null) {
            return false;
        }
        if (movie.secondaryActors.contains(actor)) {
            movie.secondaryActors.remove(actor);
            return true;
        }
        return false;
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////      DA52 - EXAMEN FINAL           ////////////////////////////////
//////////////////////      ARTAUD ALEXANDRE - BAR VALENTIN         ////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////