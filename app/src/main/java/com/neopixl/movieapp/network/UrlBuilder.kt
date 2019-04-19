package com.neopixl.movieapp.network

import com.neopixl.movieapp.model.Image

/**
 * Created by Yvan Moté on 15/02/2019.
 */

class UrlBuilder {

    // Pour des méthodes statiques (accessible partout dans notre projet)
    companion object {


        val baseUrl = "https://comicvine.gamespot.com/api"

        val apiKey = "3e6d8a56c3f66a8ce6429ddbb55fc1f29bd5e805"

        // Méthode pour récupérer l'api de recherche
        // https://api.themoviedb.org/3/search/movie?api_key=c1ac741d5dd740f9861e794c5363b0c2&query=titanic

        fun searchUrl(query: String): String {
            return "$baseUrl/movies/?api_key=$apiKey&filter=name:$query"
        }

        fun listUrl(): String {
            return "$baseUrl/movies/?api_key=$apiKey&format=json"
        }

        fun posterPathUrl(image: Image?): String {
            val posterPath = image?.thumb_url ?: ""
            return posterPath
        }
    }


}