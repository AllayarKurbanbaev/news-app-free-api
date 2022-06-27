package uz.gita.newsappallayar.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newsappallayar.data.model.NewsData
import uz.gita.newsappallayar.domain.repository.AppRepository
import uz.gita.newsappallayar.presentation.viewmodel.MainViewModel
import uz.gita.newsappallayar.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repo: AppRepository
) : ViewModel(), MainViewModel {
    override val openDrawerLiveData = MutableLiveData<Unit>()
    override val closeDrawerLiveData = MutableLiveData<Unit>()
    override val setTitleLiveData = MutableLiveData<String>()
    override val emptyResultLiveData = MutableLiveData<Unit>()
    override val newsLiveData = MutableLiveData<List<NewsData>>()
    override val errorLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val openDetailsFragmentLiveData = MutableLiveData<String>()

    init {
        load("all")
    }

    override fun openDrawer() {
        openDrawerLiveData.value = Unit
    }

    override fun selectAllCategories(category: String) {
//        closeDrawer()
//        progressLiveData.value = true
//        repository.getNewsByCategory(category).onEach {
//            progressLiveData.value = false
//            it.onSuccess {
//                newsLiveData.value = it
//            }
//            it.onFailure {
//                errorLiveData.value = it.message
//            }
//        }.launchIn(viewModelScope)
    }



    override fun itemTouch(model: String) {
        openDetailsFragmentLiveData.value = model
    }

    override fun load(category: String) {
        setTitleLiveData.value = category
        if (isConnected()) loadNetwork(category)
        else loadLocale(category)
    }

    private fun loadNetwork(category: String){
        closeDrawer()
        progressLiveData.value = true
        repo.getNewsFromNetwork(category).onEach {
            progressLiveData.value = false
            it.onSuccess {
                newsLiveData.value = it
            }
            it.onFailure {
                errorLiveData.value = it.message
            }
        }.launchIn(viewModelScope)
    }

    private fun loadLocale(category: String) {
        closeDrawer()
        progressLiveData.value = true
        repo.getNewsFromRoom(category).onEach {
            progressLiveData.value = false
            it.onSuccess { data ->
                if (data.isEmpty()) emptyResultLiveData.value = Unit
                newsLiveData.value = data
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message

            }
        }.launchIn(viewModelScope)
    }

    private fun closeDrawer() {
        closeDrawerLiveData.value = Unit
    }

}