package id.arajangstudio.hackernews.view.activities.main.adapter

import androidx.databinding.ObservableField
import id.arajangstudio.hackernews.core.BaseViewModel

/**
 * Created by Mohammad Rivai Sali on 10/22/2019.
 *
 * @Company Arajang Studio
 * @site www.arajangstudio.com
 * @github @rivaisali
 *
 */

class NewsListItemViewModel : BaseViewModel() {
    var bTextId = ObservableField<String>()
    var bTextTitle = ObservableField<String>()
    var bTextPoll = ObservableField<String>()
    var bTextScore = ObservableField<String>()
}