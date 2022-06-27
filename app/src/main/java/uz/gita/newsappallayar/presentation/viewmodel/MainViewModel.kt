package uz.gita.newsappallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.newsappallayar.data.model.NewsData

interface MainViewModel {

    val openDrawerLiveData: LiveData<Unit>
    val closeDrawerLiveData: LiveData<Unit>

    val setTitleLiveData: LiveData<String>

    val emptyResultLiveData: LiveData<Unit>
    val newsLiveData: LiveData<List<NewsData>>
    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val openDetailsFragmentLiveData: LiveData<String>

    fun openDrawer()
    fun selectAllCategories(category: String)
    fun itemTouch(model: String)

    fun load(category: String)
}