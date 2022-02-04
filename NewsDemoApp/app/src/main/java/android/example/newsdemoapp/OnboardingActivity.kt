package android.example.newsdemoapp

import android.content.Intent
import android.example.newsdemoapp.adapters.ViewPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val imagesList = listOf(
            R.drawable.first,
            R.drawable.second
        )

        val titleList = listOf(
            R.string.heading_first,
            R.string.heading_second
        )

        val deccriptionList = listOf(
            R.string.description_first,
            R.string.description_second
        )


        val adapter = ViewPagerAdapter(imagesList, titleList, deccriptionList)
        viewPager2 = findViewById(R.id.slideViewPager)
        viewPager2.adapter = adapter
    }

    fun onClickSkip(view: android.view.View) {
        val intent = Intent(this, NewsListActivity::class.java)
        startActivity(intent)
    }
}