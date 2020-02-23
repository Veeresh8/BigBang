package com.droid.bigbang

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.droid.bigbang.models.Photo

@EpoxyModelClass(layout = R.layout.item_photo_detail_view)
abstract class PhotoDetailView: EpoxyModelWithHolder<PhotoDetailView.Holder>() {

    @EpoxyAttribute
    lateinit var photo: Photo

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(photo) {
            Glide.with(holder.photoView.context).load(this.url).placeholder(R.mipmap.ic_launcher).into(holder.photoView)
            holder.photoMeta.text = getPhotoMeta(this)
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var photoView: ImageView
        lateinit var photoMeta: TextView
        override fun bindView(itemView: View) {
            photoView = itemView.findViewById(R.id.ivPhotoDetailView)
            photoMeta = itemView.findViewById(R.id.tvPhotoMetadata)
        }
    }
}