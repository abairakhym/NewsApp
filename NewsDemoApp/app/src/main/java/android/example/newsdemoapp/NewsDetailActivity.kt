package android.example.newsdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val title: TextView = findViewById(R.id.tv_title)
        val description: TextView = findViewById(R.id.tv_description)
        val img: ImageView = findViewById(R.id.iv_image)

        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            title.setText(bundle.getString("title"))
            description.setText(bundle.getString("description"))
            Glide.with(this).load(bundle.getString("img")).centerCrop().into(img)
        }
    }
}