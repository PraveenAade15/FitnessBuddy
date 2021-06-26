package com.example.myfitnessbuddy.Fragments

//Vishvakumar Mavani
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import project.st991497190.vishvakumar.R
import project.st991497190.vishvakumar.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentArticleBinding>(
            inflater,
            R.layout.fragment_article, container, false
        )

        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("https://www.healthline.com/nutrition/does-exercise-cause-weight-loss")
        binding.webView.settings.javaScriptEnabled = true

        return binding.root
    }


}