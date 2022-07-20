package by.sergey.newsapi.api

import by.sergey.newsapi.models.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("q") title: String,
        @Query("from") date: String,
        @Query("pageSize") size: String,
        @Query("sortBy") sort: String,
        @Query("apiKey") key: String
    ): Response<ArrayList<Article>>
}