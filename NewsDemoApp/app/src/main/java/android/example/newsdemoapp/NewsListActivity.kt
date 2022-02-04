package android.example.newsdemoapp

import android.content.Intent
import android.example.newsdemoapp.adapters.RecyclerViewAdapter
import android.example.newsdemoapp.api.APIRequest
import android.example.newsdemoapp.api.New
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://api.currentsapi.services"

class NewsListActivity : AppCompatActivity() {

    private var newsList = mutableListOf<New>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        recyclerView = findViewById(R.id.rv_recyclerView)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        doAPIRequest()
    }

    private fun doAPIRequest() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getNews()

                for (res in response.news){
                    Log.d("goos","Result = $res")
                    newsList.add(res)
                }
                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                }
                //clicklistener in rv
                adapter.setOnItemClickListener(object: RecyclerViewAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {

                        val intent = Intent(this@NewsListActivity,NewsDetailActivity::class.java)
                        intent.putExtra("img",newsList[position].image)
                        intent.putExtra("title",newsList[position].title)
                        intent.putExtra("description",newsList[position].description)
                        startActivity(intent)
                    }
                })

            } catch (e: Exception) {
                //catch
                Log.d("MainActivity", e.toString())
            }
        }
    }

    private fun setUpRecyclerView() {
        adapter = RecyclerViewAdapter(newsList)
        adapter.notifyDataSetChanged()
        recyclerView.adapter = adapter
    }
}