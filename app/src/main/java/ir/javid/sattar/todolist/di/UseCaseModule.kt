package ir.javid.sattar.todolist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.javid.sattar.todolist.data.repository.useCases.AddTodoUseCaseImpl
import ir.javid.sattar.todolist.data.repository.useCases.DeleteTodoUseCaseImpl
import ir.javid.sattar.todolist.data.repository.useCases.GetTodoUseCaseImpl
import ir.javid.sattar.todolist.data.repository.useCases.PinTodoUseCaseImpl
import ir.javid.sattar.todolist.data.repository.useCases.TodoListUseCaseImpl
import ir.javid.sattar.todolist.data.repository.useCases.UpdateTodoUseCaseImpl
import ir.javid.sattar.todolist.features.todoList.domain.AddTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.DeleteTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.GetTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.PinTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.TodoListUseCase
import ir.javid.sattar.todolist.features.todoList.domain.UpdateTodoUseCase

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun provideTodoListUseCase(
        useCase: TodoListUseCaseImpl,
    ): TodoListUseCase

    @Binds
    fun provideAddTodoUseCase(
        useCase: AddTodoUseCaseImpl,
    ): AddTodoUseCase

    @Binds
    fun provideDeleteTodoUseCase(
        useCase: DeleteTodoUseCaseImpl,
    ): DeleteTodoUseCase

    @Binds
    fun providePinTodoUseCase(
        useCase: PinTodoUseCaseImpl,
    ): PinTodoUseCase

    @Binds
    fun provideUpdateTodoUseCase(
        useCase: UpdateTodoUseCaseImpl,
    ): UpdateTodoUseCase

    @Binds
    fun provideGetTodoUseCase(
        useCase: GetTodoUseCaseImpl,
    ): GetTodoUseCase
}