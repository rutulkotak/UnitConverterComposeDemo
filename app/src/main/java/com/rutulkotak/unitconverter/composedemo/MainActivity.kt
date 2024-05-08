package com.rutulkotak.unitconverter.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rutulkotak.unitconverter.composedemo.compose.BaseScreen
import com.rutulkotak.unitconverter.composedemo.ui.theme.UnitConverterComposeDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // With Hilt DI
    @Inject
    lateinit var factory: ConvertorViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        // Without DI
        val dao = ConverterDatabase.getInstance(application).converterDAO
        val repository = ConverterRepositoryImpl(dao)
        val factory = ConvertorViewModelFactory(repository)
        */

        setContent {
            UnitConverterComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BaseScreen(
                        factory
                    )
                }
            }
        }
    }
}