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

@EpoxyModelClass(layout = R.layout.item_photo_view)
abstract class PhotoView: EpoxyModelWithHolder<PhotoView.Holder>() {

    @EpoxyAttribute
    lateinit var photo: Photo

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(photo) {
            Glide.with(holder.photoView.context).load(this.url).into(holder.photoView)
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var photoView: ImageView
        override fun bindView(itemView: View) {
            photoView = itemView.findViewById(R.id.ivPhoto)
        }
    }
}