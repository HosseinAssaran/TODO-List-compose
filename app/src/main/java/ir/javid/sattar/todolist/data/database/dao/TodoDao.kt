package ir.javid.sattar.todolist.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ir.javid.sattar.todolist.data.database.entity.TodoItemEntity

@Dao
interface TodoDao {
    @Query("SELECT * FROM TodoItemEntity")
    fun getAllTodos(): PagingSource<Int, TodoItemEntity>

    @Query("SELECT * FROM TodoItemEntity where id = :id")
    suspend fun getTodo(id: Int): TodoItemEntity

    @Upsert
    suspend fun upsertTodos(todos: List<TodoItemEntity>)

    @Query("DELETE FROM TodoItemEntity where id in (:ids)")
    suspend fun clearAllMovies(ids: List<Int>):Int
}
