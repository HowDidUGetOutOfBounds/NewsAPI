package by.sergey.newsapi.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val DEFAULT_NEWS_SIZE_STRING = "10"

const val NEWS_SORT_ORDER_RELEVANCY = "relevancy"
const val NEWS_SORT_ORDER_PUBLISHED_AT = "publishedAt"
const val NEWS_SORT_ORDER_POPULARITY = "popularity"

const val DEFAULT_NEWS_SORT_ORDER = NEWS_SORT_ORDER_POPULARITY
/**
 * returns current date as a string with
 * following format: yyyy-MM-dd
 */
fun getCurrentDate(): String {
    return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}