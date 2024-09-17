package ir.javid.sattar.todolist.features.todoList.ui.todoMessage

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.javid.sattar.todolist.features.todoList.data.model.TodoItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalCoroutinesApi
@Composable
fun TodoMessageScreen(
    viewModel: TodoMessageViewModel,
    navController: NavHostController,
    todoId: Int
) {
    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var isPinned by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current

    val saveState by viewModel.saveState.collectAsState()

    LaunchedEffect(saveState) {
        when (saveState) {
            is SaveState.Error -> {
                Toast.makeText(
                    context,
                    (saveState as SaveState.Error).message, Toast.LENGTH_SHORT
                ).show()
            }

            SaveState.Idle -> {}
            SaveState.Success -> {
                navController.navigateUp()
            }

            is SaveState.LoadTodo -> {
                isPinned = (saveState as SaveState.LoadTodo).todoItem.isPin
                title = (saveState as SaveState.LoadTodo).todoItem.title ?: ""
                note = (saveState as SaveState.LoadTodo).todoItem.message
            }
        }
    }

    LaunchedEffect(todoId) {
        if (todoId != -1)
            viewModel.getTodo(todoId)
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.upsertTodo(
                            TodoItem(
                                id =  if (todoId != -1) todoId else 0,
                                title = title,
                                message = note,
                                isPin = isPinned
                            )
                        )
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { isPinned = !isPinned }) {
                        Icon(
                            if (isPinned) Icons.Default.Lock else Icons.Outlined.Lock,
                            contentDescription = "Pin/Unpin"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text("Title") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = note,
                onValueChange = { note = it },
                placeholder = { Text("Note") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default)
            )
        }
    }
}