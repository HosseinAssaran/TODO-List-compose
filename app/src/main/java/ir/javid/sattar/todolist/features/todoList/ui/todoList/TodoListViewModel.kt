package ir.javid.sattar.todolist.features.todoList.ui.todoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.javid.sattar.todolist.data.database.entity.toTodoItem
import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import ir.javid.sattar.todolist.features.todoList.domain.AddTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.TodoListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TodoListViewModel @Inject constructor(
    todoListUseCase: TodoListUseCase,
    addTodoUseCase: AddTodoUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            addTodoUseCase.invoke(TodoItem(title = "title", message = "message", isPin = false))
        }
    }


    val todoList: Flow<PagingData<TodoItem>> =
        todoListUseCase
            .invoke()
            .cachedIn(viewModelScope)
            .mapLatest {
                it.map { it.toTodoItem() }
            }.catch { e -> e.printStackTrace() }
}