package com.example.practica_todo_crud

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.practica_todo_crud.navigation.NavManager
import com.example.practica_todo_crud.ui.theme.PracticaTODOCRUDTheme
import com.example.practica_todo_crud.viewModel.LoginViewModel
import com.example.practica_todo_crud.viewModel.TareasViewModel
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val loginVM : LoginViewModel by viewModels()
        val tareasVM : TareasViewModel by viewModels()


        setContent {
            PracticaTODOCRUDTheme {
                NavManager(loginVM, tareasVM)
            }
        }
    }
}