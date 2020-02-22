package com.droid.bigbang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

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
        photoViewModel.fetchPhotos(R.raw.data).observe(this, Observer {  })
    }

    private fun initRecyclerView(span: Int) {
        val layoutManager = GridLayoutManager(this, span)
        photoViewController.spanCount = span
        layoutManager.spanSizeLookup = photoViewController.spanSizeLookup
        photoView.setController(photoViewController)
    }
}
