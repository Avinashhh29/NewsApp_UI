package com.example.myapplication.myapplication.newss.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.myapplication.newss.databinding.ArticlePreviewBinding
import com.example.myapplication.myapplication.newss.model.Article
import com.example.myapplication.myapplication.newss.ui.NewsViewModel

private lateinit var binding:ArticlePreviewBinding

class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){



    inner class NewsViewHolder(binding: ArticlePreviewBinding):RecyclerView.ViewHolder(binding.root)

    private val differCallBack=object:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {

            return oldItem==newItem

        }
    }
    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        binding = ArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)


    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            article.urlToImage?.let {
                Glide.with(this).load(article.urlToImage).into(binding.ivArticleImage)
            }
            binding.tvSource.text=article.source?.name
            binding.tvPublishedAt.text=article.publishedAt
            binding.tvTitle.text=article.title
            binding.tvDescription.text=article.description
            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Article)->Unit)?=null
    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener=listener
    }

}