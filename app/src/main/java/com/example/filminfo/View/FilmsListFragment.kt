package com.example.filminfo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filminfo.model.Film
import com.example.filminfo.R
import com.example.filminfo.view.adapters.FilmAdapter
import com.example.filminfo.view.adapters.GenreAdapter
import com.example.filminfo.viewModel.FilmsListViewModel
import ua.cn.stu.navigation.contract.navigator

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

    private lateinit var viewModel: FilmsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_films_list, container, false)

        arguments?.let {
            val films: List<Film>? = it.getParcelableArrayList(FILMSVALUE)
            viewModel = FilmsListViewModel(films)
        }

        val filmsAdapter = FilmAdapter { film -> navigator().showFilmInfoScreen(film) }
        view.findViewById<RecyclerView>(R.id.ListFilms).apply {
            adapter = filmsAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        val genresAdapter = GenreAdapter({ genre : String? -> viewModel.setFilmByGenre(genre)}, viewModel.genres)
        view.findViewById<RecyclerView>(R.id.ListGenres).apply {
            adapter = genresAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.films.observe(viewLifecycleOwner,{ films ->
            filmsAdapter.submitList(films)
        })

        return view
    }
}