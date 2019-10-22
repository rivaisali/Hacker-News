package id.arajangstudio.hackernews.view.activities.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.arajangstudio.hackernews.core.BaseViewModel
import id.arajangstudio.hackernews.model.NewsModel
import id.arajangstudio.hackernews.network.InitRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Mohammad Rivai Sali on 10/22/2019.
 *
 * @Company Arajang Studio
 * @site www.arajangstudio.com
 * @github @rivaisali
 *
 */

class MainPresenter : BaseViewModel() {

    private val service = InitRetrofit.makeRetrofitService()

    var idList = listOf<String>()
    private val list = mutableListOf<NewsModel>()
    private var newsList: MutableLiveData<List<NewsModel>>? = null
    private var error: MutableLiveData<Exception>? = null

    var textError = ObservableField("Error")
    val showErrorView = ObservableField(false)
    val showLoadingView = ObservableField(true)

    fun getNewsList(): LiveData<List<NewsModel>> {
        if (newsList == null)
            newsList = MutableLiveData()
        return newsList as LiveData<List<NewsModel>>
    }

    fun getIdNewsList() {
        showLoadingView.set(true)
        GlobalScope.launch(Dispatchers.Main)
        {
            val request = service.getNewsList()
            try {
                val response = request.await()
                idList = response.body()!!

                list.clear()
                idList.subList(0, 30).forEach { id ->
                    getNewsItem(id)
                }
            } catch (e: Exception) {
                error?.value = e
                showLoadingView.set(false)
            }
        }
    }

    private fun getNewsItem(idNews: String) {
        GlobalScope.launch(Dispatchers.Main)
        {
            val request = service.getNewsItem(idNews)
            try {
                val response = request.await()
                list.add(response.body()!!)
                list.sortBy { it.time }
                newsList?.value = list
            } catch (e: Exception) {
                error?.value = e
                showLoadingView.set(false)
            }
        }
    }

    fun getError(): LiveData<Exception> {
        if (error == null)
            error = MutableLiveData()
        return error as LiveData<Exception>
    }

}