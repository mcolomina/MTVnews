package com.endava.mtvnews.news

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.endava.mtvnews.R
import com.endava.mtvnews.common.utils.PicassoWrapper
import com.endava.mtvnews.net.models.NewsItem

class NewsAdapter(private val picassoWrapper: PicassoWrapper) : RecyclerView.Adapter<NewsViewHolder>() {

    private var list: List<NewsItem>? = null
    lateinit var holderCallback: ViewHolderCallback

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = list!![position]
        holder.bind(newsItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_list, parent, false)
        return NewsViewHolder(view, picassoWrapper, holderCallback)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun setList(list: List<NewsItem>) {
        this.list = list
        notifyDataSetChanged()
    }


}