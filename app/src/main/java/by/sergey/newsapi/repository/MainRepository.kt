package by.sergey.newsapi.repository

import by.sergey.newsapi.api.ApiHelper
import by.sergey.newsapi.models.Article
import by.sergey.newsapi.models.Result
import by.sergey.newsapi.models.Root
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getNews(
        title: String,
        date: String,
        size: String,
        sort: String
    ): Result<Root> {
       return apiHelper.getNews(title, date, size, sort)
    }
}