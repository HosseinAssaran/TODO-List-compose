package ir.javid.sattar.todolist.features.todoList.domain

import kotlinx.coroutines.flow.Flow

interface PinTodoUseCase {
    operator fun invoke(isPin: Boolean, todoId:Int): Flow<Int>
}