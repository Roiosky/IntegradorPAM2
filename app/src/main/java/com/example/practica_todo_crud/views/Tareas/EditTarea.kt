package com.example.practica_todo_crud.views.Tareas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica_todo_crud.ui.theme.lightBlue
import com.example.practica_todo_crud.viewModel.TareasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTarea(navController: NavController, tareaVM: TareasViewModel, idTarea: String) {
    LaunchedEffect(Unit) {
        tareaVM.getTareaById(idTarea)
    }
    val state = tareaVM.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Editar Tarea") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        tareaVM.deleteTarea(idTarea){
                            navController.popBackStack()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                    }

                    IconButton(onClick = {
                        tareaVM.updateTarea(idTarea){
                            navController.popBackStack()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                    }
                }
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .padding(10.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 16.dp, start = 16.dp, end = 16.dp,)
                ) {
                    Text(
                        text = "Nueva Tarea",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                    )
                    OutlinedTextField(
                        value = state.titulo,
                        onValueChange = { tareaVM.onValue(it, "titulo") },
                        label = {
                            Text(
                                "Título", color = Color.DarkGray, modifier = Modifier
                                    .background(color = Color.Transparent)
                            )
                        },

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent, // Fondo blanco para los campos
                            unfocusedContainerColor = Color.Transparent, // Fondo blanco para los campos
                            focusedBorderColor = Color.Black, // Borde azul transparente al enfocarse
                            unfocusedBorderColor = Color.DarkGray, // Borde azul transparente sin enfoque
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    )
                    OutlinedTextField(
                        value = state.desc,
                        onValueChange = { tareaVM.onValue(it, "desc") },
                        label = { Text("Descripción", color = Color.DarkGray) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent, // Fondo blanco para los campos
                            unfocusedContainerColor = Color.Transparent, // Fondo blanco para los campos
                            focusedBorderColor = Color.Black, // Borde azul transparente al enfocarse
                            unfocusedBorderColor = Color.DarkGray, // Borde azul transparente sin enfoque
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .height(150.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        Text(
                            text = "Estado",
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Switch(
                            checked = state.estado,
                            onCheckedChange = { newValue ->
                                tareaVM.onValue(newValue.toString(), "estado")
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White, // Color del punto cuando está activado
                                checkedTrackColor = lightBlue, // Color de fondo del switch activado

                                uncheckedThumbColor = lightBlue, // Color del punto cuando está desactivado
                                uncheckedTrackColor = Color.DarkGray, // Color de fondo del switch desactivado

                                checkedBorderColor = Color.Transparent,
                                uncheckedBorderColor = Color.Transparent,
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Button(onClick = {
                            tareaVM.updateTarea(idTarea){
                                navController.popBackStack()
                            }
                        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow, contentColor = Color.Black)) {
                            Text(text = "Actualizar",)
                        }
                        Button(onClick = {
                            tareaVM.deleteTarea(idTarea){
                                navController.popBackStack()
                            }
                        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Black)
                            ) {
                            Text(text = "Eliminar", )
                        }
                    }
                }

            }
        }
    }
}