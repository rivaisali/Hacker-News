package id.arajangstudio.hackernews.view.activities.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.arajangstudio.hackernews.R
import id.arajangstudio.hackernews.core.BaseRecycleViewAdapter
import id.arajangstudio.hackernews.core.BaseViewHolder
import id.arajangstudio.hackernews.core.ViewDataBindingOwner
import id.arajangstudio.hackernews.databinding.ItemNewsBinding
import id.arajangstudio.hackernews.model.NewsModel
import id.arajangstudio.hackernews.view.activities.detail.DetailActivity

/**
 * Created by Mohammad Rivai Sali on 10/22/2019.
 *
 * @Company Arajang Studio
 * @site www.arajangstudio.com
 * @github @rivaisali
 *
 */
class NewsListItemAdapter : BaseRecycleViewAdapter<NewsModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<NewsModel> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<NewsModel>, position: Int) {
        (holder as ViewHolder).bindData(getItem(position), position)
    }

    class ViewHolder(context: Context, view: View) :
        BaseViewHolder<NewsModel>(context, view),
        NewsListItemView,
        ViewDataBindingOwner<ItemNewsBinding> {

        override fun onClick(view: View) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("news_id", data!!.id.toString())
            context.startActivity(intent)
        }

        override lateinit var binding: ItemNewsBinding
        private var viewModel: NewsListItemViewModel? = null
        private var data: NewsModel? = null

        init {
            binding.vm = NewsListItemViewModel()
            binding.view = this
            viewModel = binding.vm
        }

        override fun bindData(data: NewsModel) {
            // ignore
        }

        fun bindData(data: NewsModel, position: Int) {
            this.data = data

            data.id.let {
                viewModel?.bTextId?.set(it.toString())
            }

            data.title.let {
                viewModel?.bTextTitle?.set(it)
            }

            data.descendants.let {
                viewModel?.bTextPoll?.set(" " + it)
            }

            data.score.let {
                viewModel?.bTextScore?.set(" " + it.toString())
            }
        }


    }
}