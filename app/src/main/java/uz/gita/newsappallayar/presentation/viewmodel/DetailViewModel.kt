package uz.gita.newsappallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.newsappallayar.data.remote.models.NewsResponse


interface DetailViewModel {

    val progressLiveData : LiveData<Boolean>
    val popBackStackLiveData : LiveData<Unit>

    val url : LiveData<NewsResponse.ArticlesData>

    fun onClickBackButton()
    fun init(model : NewsResponse.ArticlesData)

}