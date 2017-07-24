package com.endava.mtvnews.news

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.endava.mtvnews.common.utils.PicassoWrapper
import com.endava.mtvnews.net.models.NewsItem
import kotlinx.android.synthetic.main.item_news_list.view.*

class NewsViewHolder internal constructor(itemView: View, private val picassoWrapper: PicassoWrapper,
                                          private val holderCallback: ViewHolderCallback) : RecyclerView.ViewHolder(itemView) {

    var newsItem: NewsItem? = null

    private val title: TextView by lazy {
        itemView.item_title
    }
    private val author: TextView by lazy {
        itemView.item_author
    }
    private val description: TextView by lazy {
        itemView.item_description
    }
    private val time: TextView by lazy {
        itemView.item_date
    }

    private val image: ImageView by lazy {
        itemView.image_view
    }
    private val card: CardView by lazy {
        itemView.card
    }

    fun bind(newsItem: NewsItem) {
        this.newsItem = newsItem
        title.setText(newsItem.title)
        author.setText(newsItem.author)
        description.setText(newsItem.description)
        time.setText(newsItem.publishedAt)
        picassoWrapper.display(newsItem.urlToImage, image)
        card.setOnClickListener { click -> onItemClicked() }

    }

    fun onItemClicked() {
        holderCallback.openUrl(newsItem!!.url)
    }

}