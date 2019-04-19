package com.neopixl.movieapp.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Yvan Moté on 08/02/2019.
 */

// On définit un constructeur principal sans paramètre (utile pour plus tard)

// Jackson (Parser JSON utilisé ici), par défaut il échoue
// si il ne peut pas faire une correspondance sur les propriétés
// (ex : propriété manquante)

// On force Jackson à ignorer les propriétés inconnues
@JsonIgnoreProperties(ignoreUnknown = true)
class Movie() : Parcelable {
    @JsonProperty(value = "name")
    var title: String? = ""

    @JsonProperty(value = "date_added")
    var releaseDate: String? = ""

    @JsonProperty(value = "deck")
    var posterPath: String? = ""

    var overview: String? = ""

    @JsonProperty(value = "description")
    var description: String? = "No Description Found"

    @JsonProperty(value = "image")
    var image: Image? = null

    //region Parcelable methods

    constructor(parcel: Parcel) : this() {
        // lecture dans le même ordre que la méthode writeToParcel
        title = parcel.readString()
        releaseDate = parcel.readString()
        posterPath = parcel.readString()
        description = parcel.readString()
        overview = parcel.readString()
        image = parcel.readParcelable(Image::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // écriture dans le même ordre que le constructeur constructor(parcel: Parcel)
        parcel.writeString(title)
        parcel.writeString(releaseDate)
        parcel.writeString(posterPath)
        parcel.writeString(description)
        parcel.writeString(overview)
        parcel.writeParcelable(image, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

    //endregion
}