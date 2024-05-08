package com.rutulkotak.unitconverter.composedemo.data

import kotlinx.coroutines.flow.Flow

class ConverterRepositoryImpl(private val converterDAO: ConverterDAO): ConverterRepository {

    override suspend fun insertResult(result: ConversionResult) {
        converterDAO.insertResult(result)
    }

    override suspend fun deleteResult(result: ConversionResult) {
        converterDAO.deleteResult(result)
    }

    override suspend fun deleteAllResults() {
        converterDAO.deleteAll()
    }

    override fun getSavedResults(): Flow<List<ConversionResult>> {
        return converterDAO.getResults()
    }
}