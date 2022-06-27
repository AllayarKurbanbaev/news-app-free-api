package uz.gita.newsappallayar.presentation.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappallayar.R
import uz.gita.newsappallayar.data.model.NewsData
import uz.gita.newsappallayar.databinding.FragmentMainBinding
import uz.gita.newsappallayar.presentation.ui.adapter.MainAdapter
import uz.gita.newsappallayar.presentation.viewmodel.MainViewModel
import uz.gita.newsappallayar.presentation.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private var category: String = "all"

    private val adapter: MainAdapter = MainAdapter()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {


        listNews.adapter = adapter
        listNews.layoutManager = LinearLayoutManager(requireContext())

        adapter.setOnItemClickListener { model ->
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val jsonString = gsonPretty.toJson(
                NewsData(
                    model.image,
                    model.read_more,
                    model.author,
                    model.description,
                    model.title
                )
            )
            viewModel.itemTouch(jsonString)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.load(category)
        }

        actionBar.menuDrawer.setOnClickListener {
            viewModel.openDrawer()
        }

        navigationLayout.allCategories.setOnClickListener {
            category = "all"
            viewModel.load(category)
        }

        navigationLayout.business.setOnClickListener {
            category = "business"
            viewModel.load(category)
        }

        navigationLayout.sport.setOnClickListener {
            category = "sports"
            viewModel.load(category)
        }

        navigationLayout.national.setOnClickListener {
            category = "national"
            viewModel.load(category)
        }

        navigationLayout.world.setOnClickListener {
            category = "world"
            viewModel.load(category)
        }

        navigationLayout.politics.setOnClickListener {
            category = "politics"
            viewModel.load(category)
        }

        viewModel.closeDrawerLiveData.observe(viewLifecycleOwner, closeDrawerObserver)
        viewModel.openDrawerLiveData.observe(viewLifecycleOwner, openDrawerObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.newsLiveData.observe(viewLifecycleOwner, newsObserver)
        viewModel.setTitleLiveData.observe(viewLifecycleOwner, setTitleObserver)

        viewModel.openDetailsFragmentLiveData.observe(
            this@MainFragment,
            openDetailsFragmentObserver
        )

        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }

    private val setTitleObserver = Observer<String> {
        binding.actionBar.actionBarTitle.text = it
    }


    private val closeDrawerObserver = Observer<Unit> {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    private val openDetailsFragmentObserver = Observer<String> {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
        findNavController().navigate(action)
    }

    private val progressObserver = Observer<Boolean> {
        when (it) {
            false -> binding.swipeRefresh.isRefreshing = false
            else -> binding.swipeRefresh.isRefreshing = true
        }
    }

    private val openDrawerObserver = Observer<Unit> {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private val newsObserver = Observer<List<NewsData>> {
        adapter.submitList(it)
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}