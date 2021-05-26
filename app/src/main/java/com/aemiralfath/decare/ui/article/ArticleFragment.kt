package com.aemiralfath.decare.ui.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aemiralfath.decare.data.Resource
import com.aemiralfath.decare.databinding.FragmentArticleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding as FragmentArticleBinding

    private val viewModel: ArticleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        viewModel.article.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        Log.d("ARTICLE", "LOADING")
                    }
                    is Resource.Success -> {
                        Log.d("ARTICLE", "SUCCESS")
                        Log.d("ARTICLE", it.data.toString())
                    }
                    is Resource.Error -> {
                        Log.d("ARTICLE", "ERROR")
                    }
                }
            }
        })

    }

}