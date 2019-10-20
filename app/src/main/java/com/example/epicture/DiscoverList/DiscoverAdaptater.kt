package com.example.epicture.DiscoverList

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.epicture.Model.Gallery

import com.example.epicture.R

class DiscoverAdapter(private val discoverListActivity: DiscoverListActivity,
                      private val discoverList: MutableList<Gallery>) : RecyclerView.Adapter<DiscoverAdapter.MyViewHolder>() {
//    private val originalDiscoverList: MutableList<Gallery> = discoverList

//    private var fromDate: String? = null
//    private var toDate: String? = null

//    val itemSize: Int = discoverList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_card, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // loading album cover using Glide library

        holder.tvImageTitle.text = discoverList[position].title
        if (discoverList[position].ups != null)
            holder.tvImageUps.text = discoverList[position].ups.toString()
        else
            holder.tvImageUps.text = "0"
        if (discoverList[position].downs != null)
            holder.tvImageDowns.text = discoverList[position].downs.toString()
        else
            holder.tvImageDowns.text = "0"
        Glide.with(discoverListActivity)
            .load(discoverList[position].images[0].link)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.pbLoadImage.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.pbLoadImage.visibility = View.GONE
                    return false
                }
            })
            .apply(RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
            .into(holder.ivImageThumb)

        holder.itemView.setOnClickListener(View.OnClickListener {
            discoverListActivity.onGalleryItemClick(
                position
            )
        })
    }

    override fun getItemCount(): Int {
        return discoverList.size
    }

/*    fun setFilterParameter(fromDate: String, toDate: String) {
        this.fromDate = fromDate
        this.toDate = toDate
    }*/

/*    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {

                var filteredResults: List<Movie>? = null
                if (fromDate!!.isEmpty() || toDate!!.isEmpty()) {
                    filteredResults = originalMovieList
                } else {
                    filteredResults = getFilteredResults(fromDate, toDate)
                }

                val results = Filter.FilterResults()
                results.values = filteredResults

                return results
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: Filter.FilterResults
            ) {
                movieList = filterResults.values as List<Movie>
                this@MoviesAdapter.notifyDataSetChanged()

                if (itemCount == 0) {
                    movieListActivity.showEmptyView()
                } else {
                    movieListActivity.hideEmptyView()
                }
            }
        }
    }*/

/*    protected fun getFilteredResults(fromDate: String?, toDate: String?): List<Movie> {
        val results = ArrayList<Movie>()

        for (item in originalMovieList) {

            if (item.getReleaseDate().isEmpty()) {
                continue
            }

            val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            var minDate: Date? = null
            var maxDate: Date? = null
            var releaseDate: Date? = null
            try {
                minDate = format.parse(fromDate)
                maxDate = format.parse(toDate)
                releaseDate = format.parse(item.getReleaseDate())
            } catch (e: ParseException) {
                e.printStackTrace()
                continue
            }

            if (releaseDate!!.after(minDate!!) && releaseDate.before(maxDate!!)) {
                results.add(item)
            }
        }
        return results
    }
*/

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvImageTitle: TextView = itemView.findViewById(R.id.tv_image_title)
        var tvImageUps: TextView = itemView.findViewById(R.id.tv_image_ups)
        var tvImageDowns: TextView = itemView.findViewById(R.id.tv_image_downs)
        var ivImageThumb: ImageView = itemView.findViewById(R.id.iv_image_thumb)
        var pbLoadImage: ProgressBar = itemView.findViewById(R.id.pb_load_image)
    }
}
