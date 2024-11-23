package com.example.filminfo.View

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filminfo.Model.Film
import com.example.filminfo.R
import com.example.filminfo.View.Contracts.HasBackButton
import com.example.filminfo.ViewModel.FilmInfoViewModel
import ua.cn.stu.navigation.contract.HasCustomTitle



class FilmInfoFragment : Fragment(), HasCustomTitle, HasBackButton {

    companion object {

        @JvmStatic
        private val FILMVALUE = "MOVIES VALUE"

        @JvmStatic
        fun newInstance(film: Film): FilmInfoFragment {
            val args = Bundle().apply {
                putParcelable(FILMVALUE, film)
            }
            val fragment = FilmInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var nameFilm : String
    private val viewModel: FilmInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_film_info, container, false)

        arguments?.let {
            val film: Film? = it.getParcelable(FILMVALUE)
            nameFilm = film!!.name
        }
        return view
    }

    override fun getTitle(): String {
        return  nameFilm
    }
}