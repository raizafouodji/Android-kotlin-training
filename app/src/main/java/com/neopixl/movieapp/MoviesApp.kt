package com.neopixl.movieapp

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by Yvan Moté on 15/02/2019.
 */

// Classe centrale à notre application
// Au dessus de toutes les activités
// Elle sera chargée avant la première activité
// et détruite après la dernière activité vivante

// Attention : cette classe doit être déclarée dans le fichier AndroidManifest
class MoviesApp : Application() {

    companion object {
        lateinit var requestQueue: RequestQueue
    }

    // Appelée une seule fois au lancement de l'app
    override fun onCreate() {
        super.onCreate()

        // Initialisation de la requestQueue
        requestQueue = Volley.newRequestQueue(this)
    }

}