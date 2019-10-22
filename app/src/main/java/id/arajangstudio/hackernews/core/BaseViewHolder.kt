package id.arajangstudio.hackernews.core

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import id.arajangstudio.hackernews.BR

/**
 * Created by Mohammad Rivai Sali on 10/22/2019.
 *
 * @Company Arajang Studio
 * @site www.arajangstudio.com
 * @github @rivaisali
 *
 */
abstract class BaseViewHolder<T>(val context: Context, view: View) : RecyclerView.ViewHolder(view) {

    init {
        if (this is ViewDataBindingOwner<*>) {
            setViewBinding(view)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, viewModel)
                binding.executePendingBindings()
            }

        }
    }

    abstract fun bindData(data: T)
}