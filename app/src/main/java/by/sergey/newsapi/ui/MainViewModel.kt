package by.sergey.newsapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.sergey.newsapi.models.Article
import by.sergey.newsapi.models.Result
import by.sergey.newsapi.models.Root
import by.sergey.newsapi.repository.MainRepository
import by.sergey.newsapi.util.DEFAULT_NEWS_SIZE_STRING
import by.sergey.newsapi.util.DEFAULT_NEWS_SORT_ORDER
import by.sergey.newsapi.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    private val _response = MutableLiveData<Result<Root>>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading
    val response: LiveData<Result<Root>>
        get() = _response

    init {
       // set loading, fetch data, set loaded flag
    }

    fun getNews(title: String,
                         date: String = getCurrentDate(),
                         size: String = DEFAULT_NEWS_SIZE_STRING,
                         sort: String = DEFAULT_NEWS_SORT_ORDER){
        viewModelScope.launch {
            _isLoading.postValue(true)
            _response.postValue(mainRepository.getNews(title, date, size, sort))
            _isLoading.postValue(false)
        }
    }

}