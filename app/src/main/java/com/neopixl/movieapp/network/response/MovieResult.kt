package com.neopixl.movieapp.network.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.neopixl.movieapp.model.Movie

/**
 * Created by Yvan Moté on 15/02/2019.
 */

// Objet root de la réponse du endpoint /movie
@JsonIgnoreProperties(ignoreUnknown = true)
class MovieResult {
    var page = 1

    // JSON: total_results, Kotlin: totalResults
    // @JsonProperty est une Annotation permettant
    // d'assurer la correspondance entre la clef dans le JSON
    // et la propriété dans notre classe Kotlin
    @JsonProperty(value = "number_of_total_results")
    var totalResults = 0

    @JsonProperty(value = "number_of_page_results")
    var totalPages = 0

    lateinit var results: Array<Movie>
}