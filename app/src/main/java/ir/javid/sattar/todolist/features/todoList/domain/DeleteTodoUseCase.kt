package ir.javid.sattar.todolist.features.todoList.domain

import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface DeleteTodoUseCase {
    operator fun invoke(item:TodoItem): Flow<Int>
}