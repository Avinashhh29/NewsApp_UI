package com.example.myapplication.myapplication.newss.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.example.myapplication.myapplication.newss.R
import com.example.myapplication.myapplication.newss.databinding.FragmentArticleBinding
import com.example.myapplication.myapplication.newss.databinding.FragmentSavedNewsBinding
import com.example.myapplication.myapplication.newss.ui.NewsActivity
import com.example.myapplication.myapplication.newss.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    lateinit var newsViewModel: NewsViewModel
    val args:ArticleFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel=(activity as NewsActivity).viewModel

        val article = args.article
        binding.articleView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)){
                WebSettingsCompat.setForceDark(binding.articleView.settings, WebSettingsCompat.FORCE_DARK_ON)
            }
        }

        binding.fab.setOnClickListener {
            newsViewModel.saveArticle(article)
            Snackbar.make(view,"Article Successfully Saved!",Snackbar.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}
