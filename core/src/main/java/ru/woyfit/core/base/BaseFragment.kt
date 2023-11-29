package ru.woyfit.core.base

import android.content.Context
import androidx.fragment.app.Fragment
import ru.woyfit.core.di.WoyfitApp
import ru.woyfit.core.di.provider.CoreProvider

abstract class BaseFragment: Fragment() {

    val coreComponent: CoreProvider by lazy {
        (requireActivity().applicationContext as WoyfitApp).getApplicationProvider()
    }
    abstract fun injectDependencies()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

}