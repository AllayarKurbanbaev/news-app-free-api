package uz.gita.newsappallayar.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappallayar.R
import uz.gita.newsappallayar.databinding.FragmentSplashBinding
import uz.gita.newsappallayar.presentation.viewmodel.SplashViewModel
import uz.gita.newsappallayar.presentation.viewmodel.impl.SplashViewModelImpl


@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openNextFragmentLiveData.observe(viewLifecycleOwner, openNextFragmentObserver)
    }

    private val openNextFragmentObserver = Observer<Unit>{
        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
    }
}