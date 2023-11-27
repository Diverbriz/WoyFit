package ru.woyfit.core.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
//    abstract val viewModel: BaseFlowViewModel
    abstract fun injectDependencies()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

}