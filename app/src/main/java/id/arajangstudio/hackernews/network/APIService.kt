package id.arajangstudio.hackernews.network

import id.arajangstudio.hackernews.model.NewsModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Mohammad Rivai Sali on 10/22/2019.
 *
 * @Company Arajang Studio
 * @site www.arajangstudio.com
 * @github @rivaisali
 *
 */

interface APIService {
    @GET("askstories.json?print=pretty")
    fun getNewsList(): Deferred<Response<List<String>>>

    @GET("item/{id}.json?print=pretty")
    fun getNewsItem(@Path("id") id: String): Deferred<Response<NewsModel>>
}