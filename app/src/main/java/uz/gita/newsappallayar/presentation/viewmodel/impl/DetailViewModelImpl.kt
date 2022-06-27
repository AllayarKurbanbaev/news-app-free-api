package uz.gita.newsappallayar.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsappallayar.data.remote.models.NewsResponse
import uz.gita.newsappallayar.presentation.viewmodel.DetailViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModelImpl @Inject constructor() : ViewModel(), DetailViewModel {
    override val progressLiveData = MutableLiveData<Boolean>()
    override val popBackStackLiveData = MutableLiveData<Unit>()
    override val url = MutableLiveData<NewsResponse.ArticlesData>()

    override fun onClickBackButton() {
        popBackStackLiveData.value = Unit
    }

    override fun init(model: NewsResponse.ArticlesData) {
        url.value = model
    }
}