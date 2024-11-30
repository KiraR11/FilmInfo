package com.example.filminfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.filminfo.model.Film
import com.example.filminfo.R
import com.example.filminfo.view.contracts.HasBackButton
import com.example.filminfo.viewModel.FilmInfoViewModel
import com.example.filminfo.databinding.FragmentFilmInfoBinding
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

    private var film : Film? = null
    private lateinit var binding: FragmentFilmInfoBinding
    private lateinit var viewModel: FilmInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        film = arguments?.getParcelable(FILMVALUE)
        if (film != null) {
            viewModel = FilmInfoViewModel(film!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_film_info, container, false)

        arguments?.let {
            film = it.getParcelable(FILMVALUE)!!
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_film_info, container, false)

        // Установка ViewModel в привязку
        binding.viewModel = viewModel

        // Установка жизненного цикла
        binding.lifecycleOwner = this


        return binding.root
    }

    override fun getTitle(): String {
        return  film?.name ?: "..."
    }
}