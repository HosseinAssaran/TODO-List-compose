package ir.javid.sattar.todolist.data.repository.useCases

import ir.javid.sattar.todolist.data.repository.TodoRepository
import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import ir.javid.sattar.todolist.features.todoList.domain.AddTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.DeleteTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.GetTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.PinTodoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTodoUseCaseImpl @Inject constructor(
    private val repository: TodoRepository
) : GetTodoUseCase {
    override fun invoke(todoId: Int): Flow<TodoItem> =
        repository.getTodo(todoId)
            .flowOn(Dispatchers.IO)
}