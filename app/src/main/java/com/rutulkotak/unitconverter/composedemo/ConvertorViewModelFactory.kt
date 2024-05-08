package com.rutulkotak.unitconverter.composedemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rutulkotak.unitconverter.composedemo.data.ConverterRepository
import javax.inject.Inject

class ConvertorViewModelFactory @Inject constructor(
    private val converterRepository: ConverterRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConvertorViewModel(converterRepository) as T
    }
}