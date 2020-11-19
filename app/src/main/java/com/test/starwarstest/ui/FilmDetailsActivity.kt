package com.test.starwarstest.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.test.starwarstest.R
import com.test.starwarstest.utils.*
import com.test.starwarstest.viewmodels.FilmsViewModel
import kotlinx.android.synthetic.main.activity_film_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class FilmDetailsActivity : AppCompatActivity() {
    private val filmsViewModel by viewModel<FilmsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details)
        setSupportActionBar(toolbar_film_details)
        filmsViewModel.getFilm(intent.getIntExtra(EXTRA_POSITION, 0))
        filmsViewModel.film.observe(this, Observer { film ->
            film?.run {
                progress_bar_details.visibility = View.GONE
                supportActionBar?.let {
                    it.setDisplayShowHomeEnabled(true)
                    it.setDisplayHomeAsUpEnabled(true)
                    it.title = title
                }
                valueName.text = title
                valueReleaseDate.text =
                    release_date.formatDate(FORMAT_SHORT_DATE, FORMAT_SHORT_SLASH_DATE)
                valueDirector.text = director
                valueProducer.text = producer
                valueOpeningCraw.text = opening_crawl
                valueCreated.text = created.formatDate(FORMAT_LONG_DATE, FORMAT_SHORT_SLASH_DATE)
                valueEdited.text = edited.formatDate(FORMAT_LONG_DATE, FORMAT_SHORT_SLASH_DATE)
            }
        })
        filmsViewModel.showError.observe(this, Observer {
            progress_bar_details.visibility = View.GONE
            if (it != null && it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}