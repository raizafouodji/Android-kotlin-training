package com.neopixl.movieapp.network

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.VolleyError
import com.neopixl.movieapp.MoviesApp
import com.neopixl.movieapp.model.Movie
import com.neopixl.movieapp.network.response.MovieResult
import com.neopixl.spitfire.listener.RequestListener
import com.neopixl.spitfire.request.BaseRequest

/**
 * Created by Yvan Moté on 15/02/2019.
 */
class MovieService {

    companion object {
        // Méthode appelée pour récupérer les films à partir de l'API

        fun listMoviesAPI(success: (movies: Array<Movie>) -> Unit, failure: (error: VolleyError?) -> Unit) {

            val url = UrlBuilder.listUrl()

            val request =
                BaseRequest.Builder<MovieResult>(Request.Method.GET, url, MovieResult::class.java)
                    .listener(object : RequestListener<MovieResult> {

                        override fun onSuccess(
                            request: Request<MovieResult>,
                            response: NetworkResponse,
                            movieResult: MovieResult?
                        ) {
                            val movies = movieResult?.results ?: emptyArray()

                            // On renvoie les informations (a.k.a. le tableau de movies)

                            success(movies)
                        }

                        override fun onFailure(
                            request: Request<MovieResult>,
                            response: NetworkResponse?,
                            error: VolleyError?
                        ) {
                            failure(error)
                        }

                    }).build()

            // Envoi de la requête (3,2,1 … Go!)
            MoviesApp.requestQueue.add(request)
        }
    }

}