package com.neopixl.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.neopixl.movieapp.item.MovieItem
import com.neopixl.movieapp.model.Movie
import com.neopixl.movieapp.network.MovieService
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    // Créer un espace de stockage pour les films
    val listMovies = mutableListOf<Movie>() // liste de films

    private lateinit var moviesAdapter: FastItemAdapter<MovieItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        // On va créer un adaptateur dont le rôle sera de fournir
        // des cellules à la RecyclerView

        moviesAdapter = FastItemAdapter()

        /*for(movie in listMovies) {
            // On ajoute 1 MovieItem par Movie dans l'adapter
            moviesAdapter.add(MovieItem(movie))
        }*/

        // On lie le moviesAdapter à la RecyclerView
        // pour que la RecyclerView sache à qui demander les cellules
        moviesRecyclerView.adapter = moviesAdapter

        // Préciser à la RecyclerView comment disposer les cellules
        // grâce à un LayoutManager

        // Ici on créé un LinearLayoutManager (vertical, par défaut)
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Exemple pour afficher une liste à l'horizontal
        /*
        moviesRecyclerView.layoutManager = LinearLayoutManager(this,
            RecyclerView.HORIZONTAL, false)
        */

        // Ajout d'une ligne de séparation entre chaque cellule
        moviesRecyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        // Bonus pour afficher les cellules sous forme de grille (ici à 2 colonnes)
        //moviesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        listMovies()

        moviesAdapter.withOnClickListener { _, _, item, _ ->

            val movie = item.movie
            val intent = MovieDetailActivity.createIntent(this, movie)
            startActivity(intent)

            // On retourne true pour spécifier que l'on a intercepté le clic
            true
        }

        // Ajout d'un listener pour récupérer la recherche tapée par l'utilisateur
        // dans le champ de recherche

        searchEditText.addTextChangedListener { editable ->
            val query = editable.toString()

            //findMovies(query)


            // On filtre sur les films existants

            // boucle sur tous les titres de films
            // condition si match query <-> titre
            // on enlève tous les autres

            // filter prend en paramètre un lambda (fonction avec en paramètre un Movie et le retour
            // attendu est un booléen : true -> on conserve l'objet Movie suite au filtre,
            // false -> on ne le conserve (Au revoir) )

            // filter va boucler sur tous les films
            val filteredMovies = listMovies.filter {
                // On conserve le film si il y a une correspondance
                // entre le texte de recherche et le titre du film

                // Ici on retourne le test (Est-ce que le titre du film contient la recherche ?)
                it.title?.toLowerCase()?.contains(query.toLowerCase()) ?: false
            }

            // On va effacer la liste (RecyclerView)
            moviesAdapter.clear()

            // On affiche les nouveaux résultats (rafraichissement)

            for (movie in filteredMovies) {
                moviesAdapter.add(MovieItem(movie))
            }


        }

    }


    fun listMovies() {
        MovieService.listMoviesAPI({ resultMovies ->

            // Success
            moviesAdapter.clear()
            listMovies.addAll(resultMovies)
            for (movie in resultMovies) {
                moviesAdapter.add(MovieItem(movie))
            }

        }, {})
    }

}
