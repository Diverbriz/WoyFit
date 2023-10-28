package ru.woyfit.presentation.feature.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch
import ru.woyfit.app.App
import ru.woyfit.presentation.feature.ui.main.MainViewModel
import ru.woyfit.presentation.feature.ui.main.MainViewModelFactory
import ru.woyfit.presentation.feature.ui.theme.WoyFitTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var vmFactory: MainViewModelFactory

    private lateinit var vm: MainViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)

        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]

        setContent {
            WoyFitTheme {
                val message = vm.isMessageShowFlow.collectAsState()
                val snackbarHostState = remember { SnackbarHostState() }

                // A surface container using the 'background' color from the theme
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) {
                    Column {
                        Surface(
                            modifier = Modifier,
                            color = MaterialTheme.colorScheme.background
                        ) {
                            WeatherRowPreview()
                        }
                        LaunchedEffect(key1 = message.value){
                            launch {
                                if(message.value){
                                    snackbarHostState.showSnackbar(vm.setText())
                                }
                            }
                        }
                    }
                }
            }
        }
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