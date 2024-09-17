package ir.javid.sattar.todolist.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.javid.sattar.todolist.features.todoList.ui.todoList.TodoListScreen
import ir.javid.sattar.todolist.features.todoList.ui.todoMessage.TodoMessageScreen

@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Roots.TodoList.route
    ) {
        composable(route = Roots.TodoList.route){
            TodoListScreen(
                viewModel= hiltViewModel(),
                navController = navController
            )
        }

        composable(
            route = "${Roots.TodoMessage.route}/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: -1
            TodoMessageScreen(
                viewModel= hiltViewModel(),
                navController = navController,
                todoId = todoId)
        }

    }
}