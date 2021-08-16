package com.example.myapplication.myapplication.newss.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.myapplication.newss.model.Article


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long

    @Query("SELECT *  FROM ArticleDatabase")
    fun showArticles():LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}