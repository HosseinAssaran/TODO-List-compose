package ir.javid.sattar.todolist.features.todoList.ui.todoMessage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.javid.sattar.todolist.data.database.entity.toTodoItem
import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import ir.javid.sattar.todolist.features.todoList.domain.AddTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.DeleteTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.GetTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.PinTodoUseCase
import ir.javid.sattar.todolist.features.todoList.domain.TodoListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TodoMessageViewModel @Inject constructor(
    private val addTodoUseCase: AddTodoUseCase,
    private val getTodoUseCase: GetTodoUseCase
) : ViewModel() {

    private val _saveState = MutableStateFlow<SaveState>(SaveState.Idle)
    val saveState: StateFlow<SaveState> = _saveState

    fun upsertTodo(it: TodoItem) = viewModelScope.launch {
        try {
            addTodoUseCase.invoke(it).collectLatest {
                _saveState.value = SaveState.Success
            }
        } catch (e: Exception) {
            _saveState.value = SaveState.Error(e.message ?: "Unknown error occurred")
        }

    }
    fun getTodo(todoId: Int) = viewModelScope.launch {
        try {
            getTodoUseCase.invoke(todoId).collectLatest {
                _saveState.value = SaveState.LoadTodo(it)
            }
        } catch (e: Exception) {
            _saveState.value = SaveState.Error(e.message ?: "Unknown error occurred")
        }

    }

}

sealed class SaveState {
    data object Idle : SaveState()
    data object Success : SaveState()
    data class Error(val message: String) : SaveState()
    data class LoadTodo(val todoItem: TodoItem) : SaveState()
}