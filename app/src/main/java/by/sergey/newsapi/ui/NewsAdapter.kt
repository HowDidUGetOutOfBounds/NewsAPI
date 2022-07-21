package by.sergey.newsapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.sergey.newsapi.R
import by.sergey.newsapi.databinding.NewsListItemBinding
import by.sergey.newsapi.models.Article
import com.bumptech.glide.Glide

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: NewsListItemBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>()
    {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return if(oldItem.hashCode() != newItem.hashCode())
                false
            else
                oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Article>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
       val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.apply {
            binding.title.text = item.title;
            binding.description.text = item.description;
            Glide.with(holder.itemView.context)
                .load(item.urlToImage)
                .into(binding.icon)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}