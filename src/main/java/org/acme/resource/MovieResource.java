package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.AddActorToMovieRequest;
import org.acme.dto.MovieCreateRequest;
import org.acme.entity.Actor;
import org.acme.entity.Movie;
import org.acme.service.MovieService;

import java.util.List;
import java.util.Set;

// Ce fichier Ressource (controller) permet de définir les routes de notre API pour les films.
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    MovieService movieService;

    // Lister tous les films
    @GET
    @Path("/movies")
    public List<Movie> listMovies() {
        return movieService.listAllMovies();
    }

    // Afficher un film par ID
    @GET
    @Path("/movie/{id}")
    public Response getMovie(@PathParam("id") Long id) {
        return movieService.findMovieById(id)
                .map(m -> Response.ok(m).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // Créer un nouveau film
    @POST
    @Path("/movies")
    public Response addMovie(MovieCreateRequest dto) {
        if (dto.title == null || dto.movieType == null || dto.copies == 0 || dto.mainActorId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Champs manquants (title, movieType, copies, mainActorId)").build();
        }

        Movie created = movieService.createMovie(dto.title, dto.copies, dto.movieType, dto.mainActorId);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    // Supprimer un film par ID
    @DELETE
    @Path("/movies/{id}")
    public Response deleteMovie(@PathParam("id") Long id) {
        boolean deleted = movieService.deleteMovie(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    // Lister les acteurs secondaires d’un film par ID
    @GET
    @Path("/movies/{id}/actors")
    public Response listSecondaryActors(@PathParam("id") Long movieId) {
        Set<Actor> actors = movieService.listSecondaryActors(movieId);
        return Response.ok(actors).build();
    }

    // Ajouter un acteur secondaire à un film
    @POST
    @Path("/movies/{id}/actors")
    public Response addSecondaryActor(@PathParam("id") Long movieId, AddActorToMovieRequest request) {
        if (request.actorId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Acteur manquant")
                    .build();
        }

        boolean added = movieService.addSecondaryActor(movieId, request.actorId);
        if (!added) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Film ou acteur non existant")
                    .build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    // Supprimer un acteur secondaire d’un film
    @DELETE
    @Path("/movies/{movieId}/actors/{actorId}")
    public Response removeSecondaryActor(@PathParam("movieId") Long movieId, @PathParam("actorId") Long actorId) {
        boolean removed = movieService.removeSecondaryActor(movieId, actorId);
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND).entity("Film ou acteur non existant").build();
        }
        return Response.noContent().build();
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////      DA52 - EXAMEN FINAL           ////////////////////////////////
//////////////////////      ARTAUD ALEXANDRE - BAR VALENTIN         ////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
