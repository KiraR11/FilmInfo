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
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
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
    private lateinit  var films: List<Film>
    private lateinit  var viewModel: FilmsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация films в onCreate
        films = arguments?.getParcelableArrayList(FILMSVALUE) ?: emptyList()

        // Инициализация ViewModel после получения films
        viewModel = getViewModel { parametersOf(films) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_films_list, container, false)

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