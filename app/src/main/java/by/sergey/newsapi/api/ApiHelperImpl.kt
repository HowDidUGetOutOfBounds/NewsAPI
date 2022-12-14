package by.sergey.newsapi.api

import android.util.Log
import by.sergey.newsapi.BuildConfig.SERVER_API_KEY
import by.sergey.newsapi.models.Article
import by.sergey.newsapi.models.Result
import by.sergey.newsapi.models.Root
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getNews(
        title: String,
        date: String,
        size: String,
        sort: String
    ): Result<Root> {
        try {

            val news = apiService.getNews(
                title = title,
                date = date,
                size = size,
                sort = sort,
                key = SERVER_API_KEY
            )

            return if (news.isSuccessful && (news.body()?.articles != null || news.body()?.articles?.size!=0))
            {
                Result.Success(news.body()!!)
            } else {
                Result.Failure(news.errorBody().toString(), null)
            }
        } catch (cause: Exception) {
            Log.d("ApiHelperImpl:", "getNews: $cause")
            return Result.Failure("Network Error", null)
        }
    }
}
