package com.droid.bigbang

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.droid.bigbang.data.DataRepository
import com.droid.bigbang.models.Photo
import com.droid.bigbang.models.PhotoParseResult
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import java.io.FileNotFoundException

class MainActivityTest : KoinTest {

    private val mockDataRepository = mock<DataRepository>()

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Rule
    @JvmField
    val detailsActivity = ActivityTestRule<PhotoDetailsActivity>(PhotoDetailsActivity::class.java, false, false)

    @Before
    fun setup() {
        loadKoinModules(module(override = true) {
            factory { mockDataRepository }
        })
    }

    @Test
    fun shouldThrowFileNotFoundException() {
        val liveData = MutableLiveData<PhotoParseResult>()
        liveData.postValue(PhotoParseResult.PhotoParseException(FileNotFoundException()))

        whenever(mockDataRepository.getPhotos(any())).thenReturn(liveData)

        activity.launchActivity(null)

        onView(withId(R.id.errorLayout))
            .check(matches(isDisplayed()))

        onView(withId(R.id.photoView))
            .check(matches(not(isDisplayed())))

    }

    @Test
    fun shouldThrowMalformedJSONException() {
        val liveData = MutableLiveData<PhotoParseResult>()
        liveData.postValue(PhotoParseResult.PhotoParseException(JsonSyntaxException("Malformed JSON")))

        whenever(mockDataRepository.getPhotos(any())).thenReturn(liveData)

        activity.launchActivity(null)

        onView(withId(R.id.errorLayout))
            .check(matches(isDisplayed()))

        onView(withId(R.id.photoView))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun shouldFetchDataSuccessfully() {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext

        val photosJSON = targetContext.resources.openRawResource(R.raw.data).bufferedReader()
            .use { it.readText() }
        val photoList = Gson().fromJson(photosJSON, Array<Photo>::class.java).toList()

        val liveData = MutableLiveData<PhotoParseResult>()
        liveData.postValue(PhotoParseResult.PhotosList(photoList))

        whenever(mockDataRepository.getPhotos(any())).thenReturn(liveData)

        activity.launchActivity(null)

        onView(withId(R.id.photoView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.errorLayout))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.photoView)).check(RecyclerViewItemCountAssertion(photoList.size))

    }

    @Test
    fun shouldShowArgumentErrorInPhotoDetail() {
        detailsActivity.launchActivity(null)

        onView(withId(R.id.photoArgErrorLayout))
            .check(matches(isDisplayed()))

        onView(withId(R.id.photoDetailView))
            .check(matches(not(isDisplayed())))
    }


}