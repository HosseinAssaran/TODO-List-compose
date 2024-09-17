package ir.javid.sattar.todolist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.javid.sattar.todolist.data.database.dao.TodoDao
import ir.javid.sattar.todolist.data.database.entity.TodoItemEntity
import ir.javid.sattar.todolist.data.database.entity.toTodoItem
import ir.javid.sattar.todolist.data.database.entity.toTodoItemEntity
import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {
    override fun getPagingTodos(): Flow<PagingData<TodoItemEntity>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { dao.getAllTodos() }
        ).flow


    override fun getTodo(id: Int): Flow<TodoItem> = callbackFlow {
        val entity = dao.getTodo(id)
        trySend(entity.toTodoItem())
        awaitClose { cancel() }
    }

    override fun deleteTodo(ids: List<Int>): Flow<Int> = callbackFlow {
        val effect = dao.deleteTodos(ids)
        trySend(effect)
        awaitClose { cancel() }
    }

    override fun saveTodo(model: TodoItem): Flow<Unit> = callbackFlow {
        val effect = dao.upsertTodos(listOf(model.toTodoItemEntity()))
        trySend(effect)
        awaitClose { cancel() }
    }
}