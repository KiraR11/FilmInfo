package com.example.filminfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.filminfo.viewModel.LoadingDataViewModel
import com.example.filminfo.R
import com.google.android.material.snackbar.Snackbar
import ua.cn.stu.navigation.contract.navigator
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoadingDataFragment : Fragment() {

    companion object {
        fun newInstance() = LoadingDataFragment()
    }

    private val viewModel: LoadingDataViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_loading_data, container, false)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val layout = view.findViewById<LinearLayout>(R.id.layoutNotification)
        val snackbar = Snackbar.make(
            layout,
            R.string.network_connection_error,
            Snackbar.LENGTH_INDEFINITE
        ).setActionTextColor(ContextCompat.getColor(requireContext(), R.color.base_yellow))

        snackbar.setAction(R.string.repeat) {
            viewModel.loadMovies()
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.isVisible = isLoading
        }

        viewModel.films.observe(viewLifecycleOwner) { result ->
            result.
            onSuccess { films -> navigator().showFilmsListScreen(films) }.
            onFailure { snackbar.show() }
        }
        viewModel.loadMovies()

        return view
    }
}