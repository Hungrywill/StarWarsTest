package com.test.starwarstest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.test.starwarstest.R
import com.test.starwarstest.ui.adapters.FilmsAdapter
import com.test.starwarstest.utils.EXTRA_POSITION
import com.test.starwarstest.ui.viewmodels.FilmsViewModel
import kotlinx.android.synthetic.main.activity_films.*
import org.koin.android.viewmodel.ext.android.viewModel

class FilmsActivity : AppCompatActivity() {
    private val filmsViewModel by viewModel<FilmsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        filmsViewModel.getAllFilms()
        filmsViewModel.filmsList.observe(this, Observer {
            if (it.results.isNotEmpty() && it != null) {
                progress_bar_films.visibility = View.GONE
                val list = mutableListOf<Any>()
                val count = resources.getQuantityString(R.plurals.film_total_movies, it.count, it.count)
                list.add(count)
                list.addAll(it.results)
                recyclerViewFilms.adapter = FilmsAdapter(list) { position ->
                    with(Intent(this, FilmDetailsActivity::class.java)) {
                        putExtra(EXTRA_POSITION, position)
                        startActivity(this)
                    }
                }
            }
        })
        filmsViewModel.showError.observe(this, Observer {
            progress_bar_films.visibility = View.GONE
            if (it != null && it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }
}