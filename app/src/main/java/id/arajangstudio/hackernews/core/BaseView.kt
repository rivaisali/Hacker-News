package id.arajangstudio.hackernews.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Mohammad Rivai Sali on 10/22/2019.
 *
 * @Company Arajang Studio
 * @site www.arajangstudio.com
 * @github @rivaisali
 *
 */
interface BaseView : LifecycleOwner {

    fun <T> observeData(data: LiveData<T>, observer: Observer<T>) {
        data.observe(this, observer)
    }

    fun <T> observeData(data: LiveData<T>, onChanged: (T?) -> Unit) {
        observeData(data, Observer { onChanged(it) })
    }
}