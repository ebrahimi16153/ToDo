package com.github.com.ebrahimi16153.todo.di

import android.content.Context
import androidx.room.Room
import com.github.com.ebrahimi16153.todo.data.TodoDao
import com.github.com.ebrahimi16153.todo.data.TodoDataBase
import com.github.com.ebrahimi16153.todo.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ModuleDataBase {


    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): TodoDataBase = Room.databaseBuilder(
        context = context,
        klass = TodoDataBase::class.java,
        name = Constants.TO_DO_DATABASE
    ).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideTodoDao(dataBase: TodoDataBase):TodoDao = dataBase.todoDao


}