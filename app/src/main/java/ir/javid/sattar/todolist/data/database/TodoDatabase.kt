package ir.javid.sattar.todolist.data.database


import androidx.room.Database
import androidx.room.RoomDatabase
import ir.javid.sattar.todolist.data.database.dao.TodoDao
import ir.javid.sattar.todolist.data.database.entity.TodoItemEntity

@Database(entities = [TodoItemEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

}
