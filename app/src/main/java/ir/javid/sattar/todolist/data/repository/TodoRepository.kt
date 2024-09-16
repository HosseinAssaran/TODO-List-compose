package ir.javid.sattar.todolist.data.repository

import androidx.paging.Pager
import ir.javid.sattar.todolist.data.database.entity.TodoItemEntity
import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getPagingTodos(): Flow<Pager<Int, TodoItemEntity>>

    fun getTodo(id: Int): Flow<TodoItem>

    fun deleteTodo(ids: List<Int>): Flow<Int>

    fun saveTodo(model: TodoItem): Flow<Unit>
}