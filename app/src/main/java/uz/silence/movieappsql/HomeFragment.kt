package uz.silence.movieappsql

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.silence.movieappsql.Adapters.MovieAdapter
import uz.silence.movieappsql.CLASS.Movie
import uz.silence.movieappsql.DB.MyDbHelper
import uz.silence.movieappsql.databinding.FragmentHomeBinding
import uz.silence.movieappsql.databinding.MyDialogBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var list: ArrayList<Movie>
    lateinit var myDbHelper: MyDbHelper
    lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        myDbHelper = MyDbHelper(context!!)
        list = myDbHelper.getAllContacts()
        movieAdapter = MovieAdapter(list, object : MovieAdapter.OnMyItemClickListener {
            override fun itemClick(movie: Movie) {

                val bundle = Bundle()
                bundle.putInt("id", movie.id!!)

                findNavController().navigate(R.id.showFragment, bundle)

            }

            override fun itemClickChange(movie: Movie, position: Int) {


                val bundle = Bundle()
                bundle.putInt("id", movie.id!!)
                bundle.putInt("a", position)


                val dialog = AlertDialog.Builder(context!!)
                val myDialogBinding =
                    MyDialogBinding.inflate(layoutInflater, null, false)

                myDialogBinding.nameEt.setText(movie.nameMovie)
                myDialogBinding.authorsEt.setText(movie.authorsMovie)
                myDialogBinding.aboutEt.setText(movie.aboutMovie)
                myDialogBinding.dateEt.setText(movie.dateMovie)

                dialog.setView(myDialogBinding.root)

                dialog.setPositiveButton("Edit",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {

                            movie.nameMovie = myDialogBinding.nameEt.text.toString()
                            movie.authorsMovie = myDialogBinding.authorsEt.text.toString()
                            movie.aboutMovie = myDialogBinding.aboutEt.text.toString()
                            movie.dateMovie = myDialogBinding.dateEt.text.toString()

                            myDbHelper.updateContact(movie)
                            list[position] = movie
                            movieAdapter.notifyItemChanged(position)

                        }

                    })

                dialog.show()


            }

            override fun itemClickDelete(movie: Movie, position: Int) {

                myDbHelper.deleteContact(movie)
                list.remove(movie)
                movieAdapter.notifyItemRemoved(list.size)
                movieAdapter.notifyItemRangeChanged(position, list.size)

            }

        })

        movieAdapter.notifyItemInserted(list.size)
        movieAdapter.notifyItemRemoved(list.size)


        binding.rv.adapter = movieAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.my_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {
            R.id.menuId -> {

                findNavController().navigate(R.id.addFragment)

            }
        }

        return super.onOptionsItemSelected(item)

    }


}