package com.rutulkotak.unitconverter.composedemo.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rutulkotak.unitconverter.composedemo.ConvertorViewModel
import com.rutulkotak.unitconverter.composedemo.ConvertorViewModelFactory
import com.rutulkotak.unitconverter.composedemo.compose.converter.TopScreen
import com.rutulkotak.unitconverter.composedemo.compose.history.HistoryScreen

@Composable
fun BaseScreen(
    factory: ConvertorViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConvertorViewModel = viewModel(factory = factory)
) {
    val list = converterViewModel.getConversions()
    val historyList = converterViewModel.resultList.collectAsState(initial = emptyList())

    // Configuration.ORIENTATION_LANDSCAPE will help to identify the portrait or landscape
    // Pass the isLandScapeMode boolean to all the screen required to change the UI based on the mode
    // Once the orientation is changes, all screen will gets updated
    /*
    val configuration = LocalConfiguration.current
    val isLandScapeMode by remember { mutableStateOf(false) }
    */

    Column(
        modifier = modifier.padding(30.dp)
    ) {
        TopScreen(
            list = list,
            selectedConversion = converterViewModel.selectedConversion,
            inputText = converterViewModel.inputText,
            typedValue = converterViewModel.typedValue) { message1, message2 ->
            converterViewModel.addResult(message1 = message1, message2 = message2)
        }
        Spacer(modifier = modifier.height(20.dp))
        HistoryScreen(
            list = historyList,
            modifier = modifier,
            { item ->
                converterViewModel.removeResult(item)
            },
            {
                converterViewModel.clearAll()
            }
        )
    }
}