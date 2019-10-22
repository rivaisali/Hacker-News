package id.arajangstudio.hackernews.view.activities.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.arajangstudio.hackernews.R
import id.arajangstudio.hackernews.core.BaseActivity
import id.arajangstudio.hackernews.core.ViewDataBindingOwner
import id.arajangstudio.hackernews.databinding.ActivityMainBinding
import id.arajangstudio.hackernews.view.activities.main.adapter.NewsListItemAdapter

class MainActivity : BaseActivity(),
    MainView,
    ViewDataBindingOwner<ActivityMainBinding> {

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_main
    }


    private lateinit var viewModel: MainPresenter
    override lateinit var binding: ActivityMainBinding

    private lateinit var listAdapter: NewsListItemAdapter
    override var layoutManager: LinearLayoutManager
        get() = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        set(value) {}

    private var time: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = MainPresenter()
        viewModel = binding.vm!!

        setUI()

        observeResult()
        observeError()
    }

    private fun setUI() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setHomeButtonEnabled(false)
        }

        title = getString(R.string.title_main)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvList.addItemDecoration(divider)

        listAdapter = NewsListItemAdapter()
        binding.rvList.adapter = listAdapter
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
                listAdapter.setData(it)
                if (it.size >= 30) {
                    viewModel.showLoadingView.set(false)
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.getIdNewsList()
    }

    override fun onBackPressed() {
        if (time + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Tekan Tombol Kembali Dua Kali", Toast.LENGTH_SHORT).show()
        }
        time = System.currentTimeMillis()
    }
}
