package ir.javid.sattar.todolist.data.repository.useCases

import androidx.paging.PagingData
import ir.javid.sattar.todolist.data.database.entity.TodoItemEntity
import ir.javid.sattar.todolist.data.repository.TodoRepository
import ir.javid.sattar.todolist.features.todoList.domain.TodoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodoListUseCaseImpl @Inject constructor(
    private val repository: TodoRepository
) : TodoListUseCase {
    override operator fun invoke(): Flow<PagingData<TodoItemEntity>> =
        repository.getPagingTodos()
            .flowOn(Dispatchers.IO)
}