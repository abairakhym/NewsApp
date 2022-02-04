package android.example.newsdemoapp.adapters

import android.example.newsdemoapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(
    private val imagesList: List<Int>,
    private val textTitle: List<Int>,
    private val textDeccription: List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, parent, false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val image = imagesList[position]
        val title = textTitle[position]
        val deccription = textDeccription[position]
        holder.bind(image,title,deccription)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivSliderImage = itemView.findViewById<ImageView>(R.id.titleImage)
        private val ivSliderTextTitle = itemView.findViewById<TextView>(R.id.textTitle)
        private val ivSliderTextDescription = itemView.findViewById<TextView>(R.id.textDeccription)

        fun bind(image: Int, title : Int, deccription : Int) {
            ivSliderImage.setImageResource(image)
            ivSliderTextTitle.setText(title)
            ivSliderTextDescription.setText(deccription)
        }
    }
}