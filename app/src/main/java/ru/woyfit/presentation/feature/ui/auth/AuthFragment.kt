package ru.woyfit.presentation.feature.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import ru.woyfit.app.App
import ru.woyfit.core.base.BaseFragment
import ru.woyfit.core.viewmodel.assistedViewModels
import javax.inject.Inject

class AuthFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: AuthViewModel.Factory

    private val viewModel: AuthViewModel by assistedViewModels {
        viewModelFactory.create(arguments)
    }
    override fun injectDependencies() {
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }
    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Text(text = "Hello world.")
            }
        }
    }

}