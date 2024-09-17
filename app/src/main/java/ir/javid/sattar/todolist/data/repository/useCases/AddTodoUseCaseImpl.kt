package ir.javid.sattar.todolist.data.repository.useCases

import ir.javid.sattar.todolist.data.repository.TodoRepository
import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import ir.javid.sattar.todolist.features.todoList.domain.AddTodoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddTodoUseCaseImpl @Inject constructor(
    private val repository: TodoRepository
) : AddTodoUseCase {
    override fun invoke(item: TodoItem): Flow<Unit> =
        repository.saveTodo(item)
            .flowOn(Dispatchers.IO)
}