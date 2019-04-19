package com.neopixl.movieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.neopixl.movieapp.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    companion object {
        // Création d'une méthode statique pour générer et remplir un nouvel Intent

        private val EXTRA_MOVIE = "extra_movie"

        fun createIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Récupération de l'objet movie (transféré dans l'intent - La Mouche (1986) - David Cronenberg)
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        // On affiche le titre du film dans l'App Bar
        this.title = movie.title


        textviewdescription.text = HtmlCompat.fromHtml(movie.description ?: "", 0)
        Linkify.addLinks(textviewdescription, Linkify.ALL);
        textviewdescription.movementMethod = LinkMovementMethod.getInstance()
        textviewdescription.autoLinkMask = 0

        Glide
            .with(displayImageView)
            .load(movie.image?.screen_url ?: "")
            .into(displayImageView)
    }
}
