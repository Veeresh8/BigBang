package com.droid.bigbang

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.droid.bigbang.models.Photo
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailsActivity : AppCompatActivity() {

    private val photoViewController: PhotoViewController by inject()

    companion object {

        const val POSITION = "position"
        const val ARG_PHOTO_LIST = "arg_data"

        fun launchWith(context: Context, position: Int, photos: ArrayList<Photo>) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(POSITION, position)
            intent.putParcelableArrayListExtra(ARG_PHOTO_LIST, photos)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initRecyclerView()
        checkIntent()
    }

    private fun checkIntent() {
        val position = intent.getIntExtra(POSITION, 0)
        val photosList = intent.getParcelableArrayListExtra<Photo>(ARG_PHOTO_LIST)
        Timber.i("$position || ${photosList?.size}")
    }

    private fun initRecyclerView() {
        photoDetailView.setController(photoViewController)
    }
}
