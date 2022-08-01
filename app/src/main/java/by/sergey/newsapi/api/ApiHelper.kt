package by.sergey.newsapi.api

import by.sergey.newsapi.models.Article
import by.sergey.newsapi.models.Result
import by.sergey.newsapi.models.Root


interface ApiHelper {
    suspend fun getNews(title: String,
                        date: String,
                        size: String,
                        sort: String):Result<Root>
}