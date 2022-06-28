package uz.silence.movieappsql.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.silence.movieappsql.CLASS.Movie
import uz.silence.movieappsql.Utils.Constant

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, Constant.DB_NAME, null, Constant.DB_VERSION), DatabaseService {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table ${Constant.TABLE_NAME} (${Constant.ID} integer not null primary key autoincrement unique,${Constant.NAME} text not null,${Constant.AUTHORS} text not null,${Constant.ABOUTMOVIE} text not null,${Constant.DATEMOVIE} text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists ${Constant.TABLE_NAME}")
        onCreate(p0)
    }

    override fun addContact(movie: Movie) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.NAME, movie.nameMovie)
        contentValues.put(Constant.AUTHORS, movie.authorsMovie)
        contentValues.put(Constant.ABOUTMOVIE, movie.aboutMovie)
        contentValues.put(Constant.DATEMOVIE, movie.dateMovie)
        database.insert(Constant.TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun deleteContact(movie: Movie) {
        val database = this.writableDatabase
        database.delete(Constant.TABLE_NAME, "${Constant.ID} = ?", arrayOf("${movie.id}"))
        database.close()
    }

    override fun getAllContacts(): ArrayList<Movie> {
        val list = ArrayList<Movie>()
        val query = "select * from ${Constant.TABLE_NAME}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {

            do {

                val movie = Movie(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
                list.add(movie)

            } while (cursor.moveToNext())

        }

        return list
    }

    override fun updateContact(movie: Movie): Int {
        val database = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(Constant.ID, movie.id)
        contentValues.put(Constant.NAME, movie.nameMovie)
        contentValues.put(Constant.AUTHORS, movie.authorsMovie)
        contentValues.put(Constant.ABOUTMOVIE, movie.aboutMovie)
        contentValues.put(Constant.DATEMOVIE, movie.dateMovie)

        return database.update(
            Constant.TABLE_NAME,
            contentValues,
            "${Constant.ID} = ?",
            arrayOf(movie.id.toString())
        )
    }

    override fun getContactById(id: Int): Movie {

        val database = this.readableDatabase
        val cursor = database.query(
            Constant.TABLE_NAME,
            arrayOf(
                Constant.ID,
                Constant.NAME,
                Constant.AUTHORS,
                Constant.ABOUTMOVIE,
                Constant.DATEMOVIE
            ), "${Constant.ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor?.moveToFirst()

        return Movie(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4)
        )

    }
}