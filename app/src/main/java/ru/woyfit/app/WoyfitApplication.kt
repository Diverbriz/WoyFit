package ru.woyfit.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import ru.woyfit.R
import ru.woyfit.databinding.MainActivityBinding
import ru.woyfit.di.component.AppComponent
import ru.woyfit.presentation.feature.ui.theme.WoyFitTheme

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: MainActivityBinding
    lateinit var applicationComponent: AppComponent
        private set
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment_woyfit)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.visibility = View.GONE

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        hideFab()

    }

    fun hideFab(){
        binding.fab.visibility = View.GONE
    }

    private val mBackStackChangedListener =
        FragmentManager.OnBackStackChangedListener {
            findNavController(R.id.nav_host_fragment_woyfit).navigateUp()
        }

    fun initActionBar(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.toolbar)
        supportFragmentManager.addOnBackStackChangedListener(mBackStackChangedListener)
        if (savedInstanceState != null) {
//            updateDrawerIndicator()
        }
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_woyfit)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WoyFitTheme {
        Greeting("Android")
    }
}