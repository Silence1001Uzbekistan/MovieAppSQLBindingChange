package uz.silence.movieappsql.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.silence.movieappsql.CLASS.Movie
import uz.silence.movieappsql.databinding.RvItemBinding

class MovieAdapter(var list: ArrayList<Movie>,var onMyItemClickListener: OnMyItemClickListener) : RecyclerView.Adapter<MovieAdapter.Vh>() {

    inner class Vh(var rvItemBinding: RvItemBinding) : RecyclerView.ViewHolder(rvItemBinding.root) {

        fun onBind(movie: Movie,position: Int){

            rvItemBinding.nameRv.text = movie.nameMovie
            rvItemBinding.dateRv.text = movie.dateMovie
            rvItemBinding.authorsRv.text = movie.authorsMovie

            rvItemBinding.editRv.setOnClickListener {

                onMyItemClickListener.itemClickChange(movie, position)

            }

            rvItemBinding.deleteRv.setOnClickListener {

                onMyItemClickListener.itemClickDelete(movie, position)

            }

            rvItemBinding.root.setOnClickListener {

                onMyItemClickListener.itemClick(movie)

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list[position],position)

    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface OnMyItemClickListener {

        fun itemClick(movie: Movie)
        fun itemClickChange(movie: Movie, position: Int)
        fun itemClickDelete(movie: Movie, position: Int)

    }

}