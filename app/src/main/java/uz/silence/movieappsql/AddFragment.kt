package uz.silence.movieappsql

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.silence.movieappsql.CLASS.Movie
import uz.silence.movieappsql.DB.MyDbHelper
import uz.silence.movieappsql.databinding.FragmentAddBinding
import uz.silence.movieappsql.databinding.FragmentHomeBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        myDbHelper = MyDbHelper(context!!)

        binding.saveAdd.setOnClickListener {


            if (binding.nameAddId.text.trim().isNotEmpty() && binding.authorsAddId.text.trim()
                    .isNotEmpty() && binding.aboutAddId.text.trim()
                    .isNotEmpty() && binding.dateAddId.text.trim().isNotEmpty()
            ) {

                val name = binding.nameAddId.text.toString()
                val authors = binding.authorsAddId.text.toString()
                val aboutAdd = binding.aboutAddId.text.toString()
                val date = binding.dateAddId.text.toString()

                val movie = Movie(name, authors, aboutAdd, date)
                myDbHelper.addContact(movie)
                Snackbar.make(it, "Save", Snackbar.LENGTH_LONG).show()
                findNavController().popBackStack()

            } else {

                Snackbar.make(it, "Enter correctly", Snackbar.LENGTH_LONG).show()

            }

        }

        super.onViewCreated(view, savedInstanceState)
    }


}