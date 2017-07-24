package com.endava.mtvnews.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.endava.mtvnews.R
import com.endava.mtvnews.common.extension.app
import com.endava.mtvnews.main.MainView
import com.endava.mtvnews.net.models.NewsItem
import com.endava.mtvnews.news.di.NewsModule
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment : Fragment(), NewsView, SwipeRefreshLayout.OnRefreshListener, ViewHolderCallback {

    companion object {
        val TAG = "NewsFragment"
    }

    private val progressBar: ProgressBar by lazy {
        progress_bar
    }
    private val statusTextView: TextView by lazy {
        status_text_view
    }
    private val recyclerView: RecyclerView by lazy {
        recycler_view
    }
    private val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        swipe_layout
    }

    @Inject lateinit var adapter: NewsAdapter
    @Inject lateinit var presenter: NewsPresenter

    lateinit var mainView: MainView

    fun injectDependencies() {
        app.appComponent
                .plus(NewsModule(this))
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_news, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        swipeRefreshLayout.setOnRefreshListener(this)
        presenter.loadNews()
    }

    private fun initView() {
        adapter.holderCallback = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }

    override fun showEmptyListMsg() {
        statusTextView.setText(getString(R.string.msg_empty_list))
    }

    override fun updateList(newsItem: List<NewsItem>) {
        swipeRefreshLayout.isRefreshing = false
        progressBar.visibility = View.GONE
        statusTextView.visibility = View.GONE
        adapter.setList(newsItem)
    }

    override fun showError() {
        statusTextView.setText(getString(R.string.msg_error))
        progressBar.visibility = View.GONE
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = true
        progressBar.visibility = View.GONE
        presenter.loadNews()
    }

    override fun openUrl(url: String?) {
        mainView.openUrl(url)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}