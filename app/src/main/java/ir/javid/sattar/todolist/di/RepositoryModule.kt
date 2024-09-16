package ir.javid.sattar.todolist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.javid.sattar.todolist.data.database.TodoDatabase
import ir.javid.sattar.todolist.data.repository.TodoRepository
import ir.javid.sattar.todolist.data.repository.TodoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTodoRepository(
        database: TodoDatabase,
    ): TodoRepository {
        return TodoRepositoryImpl(database)
    }
}