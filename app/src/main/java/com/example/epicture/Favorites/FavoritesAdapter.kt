package com.example.epicture.Favorites

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

class FavoritesAdapter (private val favoritesActivity: FavoritesActivity,
                        private val favoritesList: MutableList<Gallery>) : RecyclerView.Adapter<FavoritesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_card, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // loading album cover using Glide library

        holder.tvImageTitle.text = favoritesList[position].title
        holder.tvImageUps.text = favoritesList[position].ups.toString()
        holder.tvImageDowns.text = favoritesList[position].downs.toString()
        Glide.with(favoritesActivity)
            .load(favoritesList[position].images[0].link)
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
            favoritesActivity.onGalleryItemClick(
                position
            )
        })

    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvImageTitle: TextView = itemView.findViewById(R.id.tv_image_title)
        var tvImageUps: TextView = itemView.findViewById(R.id.tv_image_ups)
        var tvImageDowns: TextView = itemView.findViewById(R.id.tv_image_downs)
        var ivImageThumb: ImageView = itemView.findViewById(R.id.iv_image_thumb)
        var pbLoadImage: ProgressBar = itemView.findViewById(R.id.pb_load_image)
    }
}