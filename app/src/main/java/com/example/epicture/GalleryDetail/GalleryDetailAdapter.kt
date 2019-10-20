package com.example.epicture.GalleryDetail

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.epicture.Model.Image
import com.example.epicture.R

class GalleryDetailAdapter (
    private val galleryDetailActivity: GalleryDetailActivity,
    private val galleryDetailList: MutableList<Image>) : RecyclerView.Adapter<GalleryDetailAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_card, parent, false)

            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            // loading album cover using Glide library

            Glide.with(galleryDetailActivity)
                .load(galleryDetailList[position].link)
                .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.pbLoadImage.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.pbLoadImage.visibility = View.GONE
                        return false
                    }
                })
                .apply(RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(holder.ivImageThumb)
        }

        override fun getItemCount(): Int {
            return galleryDetailList.size
        }

        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var ivImageThumb: ImageView = itemView.findViewById(R.id.iv_image_thumb)
            var pbLoadImage: ProgressBar = itemView.findViewById(R.id.pb_load_image)
        }
}