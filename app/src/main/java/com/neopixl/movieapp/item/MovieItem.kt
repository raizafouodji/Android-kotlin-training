package com.neopixl.movieapp.item

import android.view.View
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.neopixl.movieapp.R
import com.neopixl.movieapp.model.Movie
import com.neopixl.movieapp.network.UrlBuilder
import kotlinx.android.synthetic.main.cell_movie.view.*

/**
 * Created by Yvan Moté on 08/02/2019.
 */

// Cette classe au accès aux données (objet de type Movie)
// + un constructeur prenant en paramètre un objet de type Movie
class MovieItem(val movie: Movie) : AbstractItem<MovieItem, MovieItem.MovieViewHolder>() {

    // Retourne l'id de l'item
    override fun getType(): Int {
        // On retourne un id unique en rapport avec la cellule
        // Par exemple l'id d'un label à l'intérieur de la cellule
        return R.id.movieTitleTextView
    }

    // Méthode appelée un nombre limité de fois
    // A chaque fois qu'on aura besoin d'une nouvelle cellule (View)
    // Donc par exemple environ 10 fois
    override fun getViewHolder(v: View): MovieViewHolder {
        // On appelle le constructeur MovieViewHolder en lui passant la vue fournie
        // pour retourner un nouvel objet de type MovieViewHolder
        return MovieViewHolder(v)
    }

    // Méthode appelée pour récupérer le layout
    override fun getLayoutRes(): Int {
        return R.layout.cell_movie // = Int -> lien vers le fichier layout dans les ressources
    }

    // Cette classe va correspondre à la cellule graphique (View)
    // On aura potentiellement un nombre limité de ViewHolder créés (ex : 10 maximum)
    class MovieViewHolder(itemView: View) : FastAdapter.ViewHolder<MovieItem>(itemView) {

        // Pour nettoyer / recycler la cellule
        override fun unbindView(item: MovieItem) {
            itemView.movieTitleTextView.text = null
            itemView.movieReleaseDateTextView.text = null
        }

        // Pour mettre à jour l'affichage de la cellule
        // Appelée automatiquement 🎉🎊🥳🎁
        override fun bindView(item: MovieItem, payloads: MutableList<Any>) {
            val movie = item.movie

            // On va récupérer chacune des informations de notre movie
            // et on les affiche dans les composants de la cellule (label, …)

            itemView.movieTitleTextView.text = movie.title
            itemView.movieReleaseDateTextView.text = movie.releaseDate

            Glide
                .with(itemView.posterImageView)
                .load(UrlBuilder.posterPathUrl(movie.image))
                .into(itemView.posterImageView)
        }
    }
}