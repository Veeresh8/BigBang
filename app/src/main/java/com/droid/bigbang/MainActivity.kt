package com.droid.bigbang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.droid.bigbang.models.PhotoParseResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.retry_layout.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val photoViewController: PhotoViewController by inject()
    private val photoViewModel: PhotoViewModel by inject()
    private val SPAN_COUNT = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView(SPAN_COUNT)
        fetchPhotos()
    }

    private fun fetchPhotos() {
        photoViewModel.fetchPhotos(R.raw.data).observe(this, Observer {
            when (it) {
                is PhotoParseResult.PhotoParseException -> {
                    handleError(it)
                }
                is PhotoParseResult.PhotosList -> {
                    showPhotos(it)
                }
            }
        })
    }

    private fun showPhotos(parseResult: PhotoParseResult.PhotosList) {
        photoViewController.photoList = parseResult.photoList.toMutableList()
    }

    private fun handleError(exception: PhotoParseResult.PhotoParseException) {
        Timber.e("Exception parsing resources ${exception.parseException.message}")
        photoView.gone()
        errorLayout.visible()
        tvErrorMessage.text = resources.getString(R.string.error_message)
    }

    private fun initRecyclerView(span: Int) {
        val layoutManager = GridLayoutManager(this, span)
        photoViewController.spanCount = span
        layoutManager.spanSizeLookup = photoViewController.spanSizeLookup
        photoView.layoutManager = layoutManager
        photoView.setController(photoViewController)
    }
}
