package uz.gita.newsappallayar.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappallayar.R
import uz.gita.newsappallayar.data.remote.models.NewsResponse
import uz.gita.newsappallayar.databinding.FragmentWebViewBinding
import uz.gita.newsappallayar.presentation.viewmodel.DetailViewModel
import uz.gita.newsappallayar.presentation.viewmodel.impl.DetailViewModelImpl

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_web_view) {

    private val binding by viewBinding(FragmentWebViewBinding::bind)
    private val viewModel: DetailViewModel by viewModels<DetailViewModelImpl>()
    private val navArgs: DetailFragmentArgs by navArgs()

    private lateinit var model: NewsResponse.ArticlesData
    private lateinit var str: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        actionBar.back.setOnClickListener {
            viewModel.onClickBackButton()
        }

        str = navArgs.model
        val gson = Gson()
        model = gson.fromJson(str, NewsResponse.ArticlesData::class.java)

        viewModel.init(model)

        viewModel.popBackStackLiveData.observe(viewLifecycleOwner, popBackStackObserver)
        viewModel.url.observe(viewLifecycleOwner, urlObserver)
    }

    private val popBackStackObserver = Observer<Unit> {
        requireActivity().onBackPressed()
    }

    private val urlObserver = Observer<NewsResponse.ArticlesData> {
        when {
            it.read_more != null -> {
                binding.noDataPlaceholder.visibility = GONE
                binding.webView.visibility = VISIBLE
                binding.actionBar.actionBarTitle.text = it.author
                binding.webView.loadUrl(it.read_more)
            }
            else -> {
                binding.noDataPlaceholder.visibility = VISIBLE
                binding.webView.visibility = GONE
            }
        }
    }


}