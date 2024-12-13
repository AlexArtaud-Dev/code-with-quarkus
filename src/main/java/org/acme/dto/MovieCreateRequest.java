package org.acme.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

// Ce fichier DTO permet de définir un objet qui sera utilisé pour les requêtes POST de création d'un film.
@Schema(name = "MovieCreateRequest", description = "Requête pour créer un nouveau film.")
public class MovieCreateRequest {

    @Schema(required = true, description = "Titre du film")
    public String title;

    @Schema(required = true, description = "Nombre de copies disponibles")
    public int copies;

    @Schema(required = true, description = "Catégorie du film ('ACTION', 'HORREUR'...)")
    public String movieType;

    @Schema(required = true, description = "ID de l'acteur principal")
    public Long mainActorId;
}

////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////      DA52 - EXAMEN FINAL           ////////////////////////////////
//////////////////////      ARTAUD ALEXANDRE - BAR VALENTIN         ////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
