package com.test.starwarstest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.starwarstest.R
import com.test.starwarstest.data.Film
import com.test.starwarstest.utils.FORMAT_SHORT_DATE
import com.test.starwarstest.utils.FORMAT_SHORT_SLASH_DATE
import com.test.starwarstest.utils.formatDate
import kotlinx.android.synthetic.main.item_film.view.*
import kotlinx.android.synthetic.main.item_header.view.*

class FilmsAdapter(private val items: List<Any>, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<GenericViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder =
        when (viewType) {
            HEADER_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(view)
            }
            FILM_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
                FilmViewHolder(view)
            }
            else -> throw IllegalArgumentException("should not happen")
        }


    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class HeaderViewHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun <T> bind(t: T) {
            val header = t as String

            itemView.tvTotalMovies.text = header
        }
    }

    inner class FilmViewHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun <T> bind(t: T) {
            val film = t as Film
            itemView.valueName.text = film.title
            itemView.valueReleaseDate.text = film.release_date.formatDate(FORMAT_SHORT_DATE, FORMAT_SHORT_SLASH_DATE)
            itemView.valueDirector.text = film.director
            itemView.valueProducer.text = film.producer
            itemView.valueDescription.text = film.opening_crawl

            itemView.setOnClickListener {
                listener(adapterPosition)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (items[position]) {
            is String -> return HEADER_TYPE
            is Film -> return FILM_TYPE
        }
        return super.getItemViewType(position)
    }

    companion object {
        const val HEADER_TYPE = 1
        const val FILM_TYPE = 2
    }
}

/**
 * Abstract GenericViewHolder
 */
abstract class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * Bind the data
     *
     * @param data Data to display
     */
    abstract fun <T> bind(t: T)
}