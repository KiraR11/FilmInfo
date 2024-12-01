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
import org.koin.androidx.viewmodel.ext.android.viewModel
import ua.cn.stu.navigation.contract.HasCustomTitle
import org.koin.core.parameter.parametersOf


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

    private val film: Film? by lazy { arguments?.getParcelable(FILMVALUE)}
    private lateinit var binding: FragmentFilmInfoBinding
    private val viewModel: FilmInfoViewModel by viewModel { parametersOf(film) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_film_info, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun getTitle(): String {
        return  film?.name ?: "..."
    }
}