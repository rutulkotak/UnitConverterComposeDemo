package com.rutulkotak.unitconverter.composedemo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rutulkotak.unitconverter.composedemo.data.Conversion
import com.rutulkotak.unitconverter.composedemo.data.ConversionResult
import com.rutulkotak.unitconverter.composedemo.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConvertorViewModel(private val converterRepository: ConverterRepository): ViewModel() {

    val resultList = converterRepository.getSavedResults()
    val selectedConversion: MutableState<Conversion?> = mutableStateOf(null)
    val inputText: MutableState<String> = mutableStateOf("")
    val typedValue = mutableStateOf("0.0")

    fun getConversions() = listOf(
        Conversion(id = 1, description = "Pounds to Kilograms", convertFrom = "lbs", convertTo = "kg", multiplyBy = 0.453592),
        Conversion(id = 2, description = "Kilograms to Pounds", convertFrom = "kg", convertTo = "lbs", multiplyBy = 2.20462),
        Conversion(id = 3, description = "Yards to Maters", convertFrom = "yd", convertTo = "m", multiplyBy = 0.9144),
        Conversion(id = 4, description = "Maters to Yards", convertFrom = "m", convertTo = "yd", multiplyBy = 1.09361),
        Conversion(id = 5, description = "Miles to Kilometers", convertFrom = "mi", convertTo = "km", multiplyBy = 1.60934),
        Conversion(id = 6, description = "Kilometers to Miles", convertFrom = "km", convertTo = "mi", multiplyBy = 0.621371)
    )

    fun addResult(message1: String, message2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.insertResult(ConversionResult(0, message1, message2))
        }
    }

    fun removeResult(conversionResult: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.deleteResult(conversionResult)
        }
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.deleteAllResults()
        }
    }
}