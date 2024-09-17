package ir.javid.sattar.todolist.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun TodoTopBar(
    title: String,
    addClick: ()->Unit
) {

    TopAppBar(
        title = {
          Text(text = title)
        },
        actions = {
                IconButton(
                    onClick = {
                        addClick.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add todo"
                    )
                }
        }
    )
}