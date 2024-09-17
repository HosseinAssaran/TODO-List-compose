package ir.javid.sattar.todolist.data.repository.useCases

import ir.javid.sattar.todolist.data.repository.TodoRepository
import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import ir.javid.sattar.todolist.features.todoList.domain.AddTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.DeleteTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.PinTodoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PinTodoUseCaseImpl @Inject constructor(
    private val repository: TodoRepository
) : PinTodoUseCase {
    override fun invoke(isPin: Boolean, todoId:Int): Flow<Int> =
        repository.pinTodo(isPin, todoId)
            .flowOn(Dispatchers.IO)
}