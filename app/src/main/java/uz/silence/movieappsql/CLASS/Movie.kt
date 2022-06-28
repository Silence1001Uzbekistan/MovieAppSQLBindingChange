package uz.silence.movieappsql.CLASS

import java.io.Serializable

class Movie:Serializable {

    var id: Int? = null
    var nameMovie: String? = null
    var authorsMovie: String? = null
    var aboutMovie: String? = null
    var dateMovie: String? = null


    constructor(
        id: Int?,
        nameMovie: String?,
        authorsMovie: String?,
        aboutMovie: String?,
        dateMovie: String?
    ) {
        this.id = id
        this.nameMovie = nameMovie
        this.authorsMovie = authorsMovie
        this.aboutMovie = aboutMovie
        this.dateMovie = dateMovie
    }

    constructor(
        nameMovie: String?,
        authorsMovie: String?,
        aboutMovie: String?,
        dateMovie: String?
    ) {
        this.nameMovie = nameMovie
        this.authorsMovie = authorsMovie
        this.aboutMovie = aboutMovie
        this.dateMovie = dateMovie
    }

    constructor()


}