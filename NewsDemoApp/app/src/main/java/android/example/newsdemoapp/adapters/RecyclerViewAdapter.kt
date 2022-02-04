package android.example.newsdemoapp.adapters


import android.example.newsdemoapp.R
import android.example.newsdemoapp.api.New
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter (
    private val data: List<New>)
    : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>()  {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class MyViewHolder(val view: View,val listener: onItemClickListener): RecyclerView.ViewHolder(view){

        fun bind(new: New){
            val title = view.findViewById<TextView>(R.id.tv_title)
            val imageView = view.findViewById<ImageView>(R.id.iv_image)
            val description = view.findViewById<TextView>(R.id.tv_description)

            title.text = new.title
            description.text = new.description

            Glide.with(view.context).load(new.image).centerCrop().into(imageView)

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(v, mListener)
    }

    override fun getItemCount(): Int {
        return 18
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }
}