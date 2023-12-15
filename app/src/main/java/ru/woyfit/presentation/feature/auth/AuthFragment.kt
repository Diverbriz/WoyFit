package ru.woyfit.presentation.feature.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import ru.woyfit.app.App
import ru.woyfit.core.base.BaseFragment
import ru.woyfit.core.viewmodel.assistedViewModels
import ru.woyfit.core.viewmodel.lazyViewModel
import javax.inject.Inject

class AuthFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: AuthViewModel.Factory

    private val viewModel: AuthViewModel by lazyViewModel { stateHandle ->
        (requireActivity().applicationContext as App).appComponent.inject().create(stateHandle)
    }
    override fun injectDependencies() {
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    fun subscribeLiveData(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.showMessage.collect { data ->
                if(data.isNotEmpty()){
                    showMessage(data)
                }
            }
        }
    }

    fun showMessage(message:String){
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLiveData()
        (view as ComposeView). setContent {
            AuthFragmentView(viewModel.isLoginView, viewModel.listener)
        }
    }
    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
    }

}