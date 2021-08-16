package com.example.myapplication.myapplication.newss.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.myapplication.newss.R
import com.example.myapplication.myapplication.newss.databinding.ActivityNewsBinding
import com.example.myapplication.myapplication.newss.db.ArticleDatabase
import com.example.myapplication.myapplication.newss.repository.Repository

class NewsActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewsBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        val repo =Repository(ArticleDatabase(this))
        val viewModelProviderFactory=NewsViewModelProviderFactory(repo)
        viewModel=ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)
        val bottomNavigationMenu=binding.bottomNavigationView
        val navController= supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)!!.findNavController()
        bottomNavigationMenu.setupWithNavController(navController)
    }
}