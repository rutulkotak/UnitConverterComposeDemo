package com.rutulkotak.unitconverter.composedemo.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.rutulkotak.unitconverter.composedemo.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typedValue: MutableState<String>,
    save:(String, String) -> Unit
) {

    /*
    If we keep state, here then it will be reset when activity restart like configuration change
    Moving states to the ViewModel is a good option

    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = remember { mutableStateOf("") }
    val typedValue = remember { mutableStateOf("0.0") }
    */

    // typedValue, selectedConversion, etc are MutableState values which gets values from ViewModel
    // After configuration change, TopScreen() will be called again with values
    // Hence, Compose runtime recognizes that as a state update and executes save() code part again and multiple DB entries
    var toSave by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        // Dropdown menu for list of conversion options
        ConversionMenu(list = list) {
            selectedConversion.value = it
            typedValue.value = "0.0"
            inputText.value = ""
        }

        // When user selects any option, show InputBlock to get user input
        selectedConversion.value?.let {
            InputBlock(conversion = it, inputText = inputText) { input ->
                typedValue.value = input
                toSave = true
            }
        }

        // When user type input and click on "Convert", show result
        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            val multiply = selectedConversion.value?.multiplyBy ?: 0.0
            val result = input * multiply

            // Rounding of the result to 4 decimal places
            val df = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN
            val roundedResult = df.format(result)

            val message1 = "${typedValue.value} ${selectedConversion.value?.convertFrom} is equal to"
            val message2 = "$roundedResult ${selectedConversion.value?.convertTo}"
            if (toSave) {
                // Save to Database
                save(message1, message2)
                toSave = false
            }
            ResultBlock(message1 = message1, message2 = message2)
        }
    }
}