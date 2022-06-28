package uz.silence.movieappsql

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.silence.movieappsql.Adapters.MovieAdapter
import uz.silence.movieappsql.CLASS.Movie
import uz.silence.movieappsql.DB.MyDbHelper
import uz.silence.movieappsql.databinding.FragmentHomeBinding
import uz.silence.movieappsql.databinding.FragmentShowBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowFragment : Fragment() {

    private var _binding: FragmentShowBinding? = null
    private val binding get() = _binding!!

    lateinit var myDbHelper: MyDbHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShowBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = arguments?.getInt("id")

        myDbHelper = MyDbHelper(context!!)

        val contactById = myDbHelper.getContactById(id!!)

        binding.movieNameId.text = contactById.nameMovie
        binding.movieAuthorsId.text = contactById.authorsMovie
        binding.movieAboutId.text = contactById.aboutMovie
        binding.movieDateId.text = contactById.dateMovie

        super.onViewCreated(view, savedInstanceState)
    }

}