package uz.gita.newsappallayar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.newsappallayar.R
import uz.gita.newsappallayar.data.model.NewsData
import uz.gita.newsappallayar.databinding.ItemBinding

class MainAdapter : ListAdapter<NewsData, MainAdapter.ViewHolder>(NewsDiffUtil) {

    private var onItemClickListener: ((NewsData) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun populateModel(model: NewsData) {
            binding.tvName.text = model.title
            binding.tvDescription.text = model.title
            Glide.with(binding.imageView)
                .load(model.image)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_error)
                .into(binding.imageView)
        }
    }


    private object NewsDiffUtil : DiffUtil.ItemCallback<NewsData>() {
        override fun areItemsTheSame(
            old: NewsData,
            New: NewsData
        ): Boolean {
            return old.title == New.title
        }

        override fun areContentsTheSame(
            old: NewsData,
            New: NewsData
        ): Boolean {
            return old == New
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(ItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateModel(getItem(position))
    }

    fun setOnItemClickListener(block: (NewsData) -> Unit) {
        onItemClickListener = block
    }
}