package com.example.myapplication.myapplication.newss.repository

import com.example.myapplication.myapplication.newss.api.RetrofitInstance
import com.example.myapplication.myapplication.newss.db.ArticleDatabase
import com.example.myapplication.myapplication.newss.model.Article

class Repository(val db:ArticleDatabase) {

    suspend fun getBreakingNews(countryCode:String,pageNumber:Int)=
        RetrofitInstance.api.getBreakingNewsPerCategory(countryCode,pageNumber)
    suspend fun searchNews(searchQuery:String,pageNumber: Int)=
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
    suspend fun upsert(article:Article)=db.getArticleDao().upsert(article)

    fun getSavedNews()=db.getArticleDao().showArticles()

    suspend fun deleteArticle(article: Article)=
        db.getArticleDao().deleteArticle(article)


}