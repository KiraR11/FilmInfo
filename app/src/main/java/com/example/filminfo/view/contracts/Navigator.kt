package ua.cn.stu.navigation.contract

import androidx.fragment.app.Fragment
import com.example.filminfo.model.Film

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showFilmInfoScreen(film: Film)

    fun showFilmsListScreen(films: List<Film>)

    fun showLoadingDataScreen()
}