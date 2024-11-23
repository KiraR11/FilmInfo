package com.example.filminfo.View

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.filminfo.Model.Film
import com.example.filminfo.ViewModel.FilmsListViewModel
import com.example.filminfo.R

class FilmsListFragment : Fragment() {

    companion object {

        @JvmStatic
        private val FILMSVALUE = "FILMS VALUE"

        @JvmStatic
        fun newInstance(films: List<Film>): FilmsListFragment {
            val args = Bundle().apply {
                putParcelableArrayList(FILMSVALUE, ArrayList(films))
            }
            val fragment = FilmsListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: FilmsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_films_list, container, false)
        val testText = view.findViewById<TextView>(R.id.testText)
        arguments?.let {
            val films: List<Film>? = it.getParcelableArrayList(FILMSVALUE)
            testText.text = films!!.joinToString("\n")

        }
        return view
    }
}