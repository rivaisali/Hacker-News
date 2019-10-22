package id.arajangstudio.hackernews.model

/**
 * Created by Mohammad Rivai Sali on 10/22/2019.
 *
 * @Company Arajang Studio
 * @site www.arajangstudio.com
 * @github @rivaisali
 *
 */
data class NewsModel(
    val id: Int,
    val title: String,
    val by: String,
    val descendants: String,
    val text: String,
    val score: Int,
    val time: Long
)