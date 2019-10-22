package id.arajangstudio.hackernews.core

/**
 * Created by Mohammad Rivai Sali on 10/22/2019.
 *
 * @Company Arajang Studio
 * @site www.arajangstudio.com
 * @github @rivaisali
 *
 */
interface ViewModelOwner<T : BaseViewModel> {
    val viewModel: T
}