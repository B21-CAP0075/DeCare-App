package com.aemiralfath.decare.ui.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.Resource
import com.aemiralfath.decare.databinding.FragmentArticleBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding as FragmentArticleBinding

    private lateinit var articleAdapter: ArticleAdapter
    private val viewModel: ArticleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleAdapter = ArticleAdapter()

        viewModel.article.observe(viewLifecycleOwner, {
            if (it != null) {
                when(it) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }
                    is Resource.Success -> {
                        showLoading(false)
                        it.data?.let { articles ->
                            articleAdapter.setData(articles)
                        }
                    }
                    is Resource.Error -> {
                        showLoading(false)
                    }
                }
            }
        })

        with(binding.rvArticle) {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = articleAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.rvArticle.visibility = View.GONE
            binding.progressBarArticle.visibility = View.VISIBLE
        }else {
            binding.rvArticle.visibility = View.VISIBLE
            binding.progressBarArticle.visibility = View.GONE
        }
    }

}