package uz.silence.movieappsql.DB

import uz.silence.movieappsql.CLASS.Movie


interface DatabaseService {

    fun addContact(movie: Movie)

    fun deleteContact(movie: Movie)

    fun getAllContacts(): ArrayList<Movie>

    fun updateContact(movie: Movie): Int

    fun getContactById(id: Int): Movie

}