package ir.javid.sattar.todolist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.withTransaction
import ir.javid.sattar.todolist.data.database.TodoDatabase
import ir.javid.sattar.todolist.data.database.entity.TodoItemEntity
import ir.javid.sattar.todolist.data.database.entity.toTodoItem
import ir.javid.sattar.todolist.data.database.entity.toTodoItemEntity
import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val database: TodoDatabase
) : TodoRepository {
    override fun getPagingTodos(): Flow<Pager<Int, TodoItemEntity>> = flow {
        val pagingSourceFactory = { database.todoDao().getAllTodos() }
        val pager = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = pagingSourceFactory
        )
        emit(pager)
    }

    override fun getTodo(id: Int): Flow<TodoItem> = callbackFlow {
        database.withTransaction {
            val entity = database.todoDao().getTodo(id)
            trySend(entity.toTodoItem())
        }
        awaitClose { cancel() }
    }

    override fun deleteTodo(ids: List<Int>): Flow<Int>  = callbackFlow {
        database.withTransaction {
            val effect = database.todoDao().deleteTodos(ids)
            trySend(effect)
        }
        awaitClose { cancel() }
    }

    override fun saveTodo(model: TodoItem): Flow<Unit> = callbackFlow {
        database.withTransaction {
            val effect = database.todoDao().upsertTodos(listOf(model.toTodoItemEntity()))
            trySend(effect)
        }
        awaitClose { cancel() }
    }
}