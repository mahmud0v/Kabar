package com.example.kabar.module

import android.content.Context
import androidx.room.Room
import com.example.kabar.database.AppDatabase
import com.example.kabar.database.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,AppDatabase::class.java,"Kabar.db")
        .build()


    @Singleton
    @Provides
    fun provideNewsDao(db:AppDatabase) = db.getNewsDao()


}