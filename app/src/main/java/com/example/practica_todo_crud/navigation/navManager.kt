package com.example.practica_todo_crud.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practica_todo_crud.viewModel.LoginViewModel
import com.example.practica_todo_crud.viewModel.TareasViewModel
import com.example.practica_todo_crud.views.Login.BlankView
import com.example.practica_todo_crud.views.Login.TabsView
import com.example.practica_todo_crud.views.Tareas.AddTarea
import com.example.practica_todo_crud.views.Tareas.EditTarea
import com.example.practica_todo_crud.views.Tareas.Tareas

@Composable
fun NavManager(loginVM: LoginViewModel, tareasVM: TareasViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Blank") {
        composable("Blank") {
            BlankView(navController)
        }
        composable("Login"){
            TabsView(navController, loginVM)
        }
        composable("Home") {
            Tareas(navController, tareasVM)
        }
        composable("AddTareas"){
            AddTarea(navController, tareasVM)
        }
        composable("EditTarea/{idTarea}", arguments = listOf(
            navArgument("idTarea") {type = NavType.StringType}
        )){
            val idTarea = it.arguments?.getString("idTarea") ?: ""
            EditTarea(navController, tareasVM, idTarea)
        }
    }
}