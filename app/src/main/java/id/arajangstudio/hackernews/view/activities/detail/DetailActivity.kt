package id.arajangstudio.hackernews.view.activities.detail

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.arajangstudio.hackernews.R
import id.arajangstudio.hackernews.core.BaseActivity
import id.arajangstudio.hackernews.core.ViewDataBindingOwner
import id.arajangstudio.hackernews.databinding.ActivityDetailBinding
import id.arajangstudio.hackernews.utils.FormatDate
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), DetailView,
    ViewDataBindingOwner<ActivityDetailBinding> {

    override var layoutManager: LinearLayoutManager
        get() = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        set(value) {}


    override fun getViewLayoutResId(): Int {
        return R.layout.activity_detail
    }

    private lateinit var news_id: String
    private lateinit var viewModel: DetailPresenter
    override lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = DetailPresenter()
        viewModel = binding.vm!!

        setUI()

        news_id = intent.getStringExtra("news_id")

        observeResult()
        observeError()
    }

    private fun setUI() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setHomeButtonEnabled(false)
        }

        title = getString(R.string.title_detail)

    }

    private fun observeError() {
        observeData(viewModel.getError()) { error ->
            error?.let {
                viewModel.textError.set(it.message.toString())
                viewModel.showErrorView.set(true)
            }
        }
    }

    /**
     * Observe result in viewModel if any data changed
     */
    private fun observeResult() {
        observeData(viewModel.getNewsList()) { data ->
            data?.let {
                titlenews.text = it[0].title
                author.text = it[0].by
                date.text = FormatDate.dateMessageFormat(it[0].time, FormatDate.EEDDMMMYYYY_HHMMZZZ)
                desc.text = it[0].text
                viewModel.showLoadingView.set(false)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.getNewsItem(news_id)
    }

}

