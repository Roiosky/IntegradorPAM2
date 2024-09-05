package com.example.practica_todo_crud.views.Tareas

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica_todo_crud.R
import com.example.practica_todo_crud.ui.theme.lightBlue
import com.example.practica_todo_crud.viewModel.TareasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTarea(navController: NavController, tareasVM: TareasViewModel) {
    var titulo by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nueva Nota")},
                // botones al lado derecho
                actions = {
                    IconButton(onClick = {
                        tareasVM.saveNewTarea(titulo, desc, estado) {
                            Toast.makeText(context, "Guardo",
                                Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    }) {
                        Icon(painter = painterResource(R.drawable.add_task), contentDescription = "")
                    }
                }
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                        value = titulo,
                        onValueChange = { titulo = it },
                        label = {
                            Text("Título", color = Color.DarkGray, modifier = Modifier
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
                        value = desc,
                        onValueChange = { desc = it },
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
                            checked = estado,
                            onCheckedChange = { estado = it },
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
                    Button(
                        onClick = {
                            tareasVM.saveNewTarea(titulo, desc, estado) {
                                Toast.makeText(context, "Guardo",
                                    Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = lightBlue // Color azul celeste para el botón
                        ),

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(text = "Enviar", color = Color.DarkGray,)
                    }
                }
            }
        }

    }
}