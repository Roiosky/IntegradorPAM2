package com.example.practica_todo_crud.views.Tareas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica_todo_crud.R
import com.example.practica_todo_crud.components.CardTarea
import com.example.practica_todo_crud.viewModel.TareasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tareas(navController: NavController, tareasVM: TareasViewModel) {

    LaunchedEffect(Unit) {
        tareasVM.getAllTareas()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "TAREAS") },
            navigationIcon = {

            },
            actions = {
                Row {
                    IconButton(onClick = {
                        navController.navigate("AddTareas")
                    }) {
                        Icon(painter = painterResource(R.drawable.add), contentDescription = "")
                    }
                    IconButton(onClick = {
                        tareasVM.signOut() // salir
                        navController.popBackStack() // salir
                    }) {
                        Icon(painter = painterResource(R.drawable.logout), contentDescription = "")
                    }
                }
            }
        )
        }
    ) {pad ->
        Column(
            modifier = Modifier.padding(pad),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val datos by tareasVM.tareaData.collectAsState()

            LazyColumn {
                items(datos) {item ->
                    CardTarea(
                        titulo = item.titulo,
                        desc = item.desc,
                        estado = item.estado.toString()) {
                        navController.navigate("EditTarea/${item.idTarea}")

                    }
                }
            }
        }

    }
}