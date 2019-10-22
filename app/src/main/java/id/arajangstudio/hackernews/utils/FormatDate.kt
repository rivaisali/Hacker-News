package id.arajangstudio.hackernews.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mohammad Rivai Sali on 10/23/2019.
 *
 * @Company Arajang Studio
 * @site www.arajangstudio.com
 * @github @rivaisali
 *
 */
object FormatDate {
    const val DDMMMMYYYY = "dd MMMM yyyy"
    const val EEDDMMMYYYY_HHMMZZZ = "EEE, dd MMM yyyy - HH:mm zzz"

    fun dateMessageFormat(timemillis: Long, newFormat: String): String {
        val sdf = SimpleDateFormat(newFormat, Locale("in"))
        val mDate = Date(timemillis)
        return sdf.format(mDate)
    }
}