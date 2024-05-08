package com.rutulkotak.unitconverter.composedemo.di

import android.app.Application
import androidx.room.Room
import com.rutulkotak.unitconverter.composedemo.data.ConverterDatabase
import com.rutulkotak.unitconverter.composedemo.data.ConverterRepository
import com.rutulkotak.unitconverter.composedemo.data.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConverterDatabase(app: Application): ConverterDatabase {
        return Room.databaseBuilder(
            app,
            ConverterDatabase::class.java,
            "converter_data_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideConverterRepository(database: ConverterDatabase): ConverterRepository {
        return ConverterRepositoryImpl(database.converterDAO)
    }
}