package ir.javid.sattar.todolist.data.repository

import ir.javid.sattar.todolist.data.database.TodoDatabase
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val database: TodoDatabase
):TodoRepository