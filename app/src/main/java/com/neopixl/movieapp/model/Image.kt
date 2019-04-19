package com.neopixl.movieapp.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Image() : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    @JsonProperty(value = "icon_url")
    var icon_url: String? = ""

    @JsonProperty(value = "medium_url")
    var medium_url: String? = ""

    @JsonProperty(value = "screen_url")
    var screen_url: String? = ""

    @JsonProperty(value = "small_url")
    var small_url: String? = ""

    @JsonProperty(value = "super_url")
    var super_url: String? = ""

    @JsonProperty(value = "thumb_url")
    var thumb_url: String? = ""

    @JsonProperty(value = "tiny_url")
    var tiny_url: String? = ""

    @JsonProperty(value = "original_url")
    var original_url: String? = ""

    constructor(parcel: Parcel) : this() {
        icon_url = parcel.readString()
        medium_url = parcel.readString()
        screen_url = parcel.readString()
        small_url = parcel.readString()
        super_url = parcel.readString()
        thumb_url = parcel.readString()
        tiny_url = parcel.readString()
        original_url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(icon_url)
        parcel.writeString(medium_url)
        parcel.writeString(screen_url)
        parcel.writeString(small_url)
        parcel.writeString(super_url)
        parcel.writeString(thumb_url)
        parcel.writeString(tiny_url)
        parcel.writeString(original_url)
    }


    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}
