package com.example.myapplication.myapplication.newss.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.myapplication.newss.util.Resource
import com.example.myapplication.myapplication.newss.model.Article
import com.example.myapplication.myapplication.newss.model.NewsResponse
import com.example.myapplication.myapplication.newss.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel (private val repository: Repository):ViewModel(){

   var breakingNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage =1
    var breakingNewsResponse:NewsResponse?=null

    var searchNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var SearchNewsResponse:NewsResponse?=null


    init {
        getBreakingNews("in")
    }

     fun getBreakingNews(countryCode: String)= viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response=repository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }
    fun searchNews(searchQuery: String)= viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response=repository.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>? {
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                breakingNewsPage++
                if (breakingNewsResponse==null){
                    breakingNewsResponse=resultResponse
                }else{
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>? {
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                searchNewsPage++
                if (SearchNewsResponse==null){
                    SearchNewsResponse=resultResponse
                }else{
                    val oldArticles = SearchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(SearchNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article)=viewModelScope.launch {
        repository.upsert(article)
    }
    fun getSavedNews()=repository.getSavedNews()

    fun deleteArticle(article: Article)=viewModelScope.launch {
        repository.deleteArticle(article)
    }



}