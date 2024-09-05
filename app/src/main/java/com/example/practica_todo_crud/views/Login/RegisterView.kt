package com.example.practica_todo_crud.views.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica_todo_crud.components.Alert
import com.example.practica_todo_crud.ui.theme.Purple80
import com.example.practica_todo_crud.viewModel.LoginViewModel

@Composable
fun RegisterView(navController: NavController, LoginVM: LoginViewModel){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var nombre by remember { mutableStateOf("")}
        var email by remember { mutableStateOf("")}
        var password by remember { mutableStateOf("")}

        ElevatedCard(
            modifier = Modifier
                .width(330.dp)
                .height(340.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = true
                ),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray,
                contentColor = Color.White
            )
        ) {

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Registrarse", fontSize = 32.sp, color = Purple80, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp)

                )

                OutlinedTextField(
                    value = nombre,
                    label = { Text(text = "nombre")},
                    onValueChange = {nombre = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                )

                OutlinedTextField(
                    value = email,
                    label = { Text(text = "email")},
                    onValueChange = {email = it},
                    placeholder = { Text("example@gmail.com") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                )

                OutlinedTextField(value = password,
                    onValueChange = {password = it},
                    label = { Text(text = "Password")},
                    placeholder = { Text(text = "password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                    LoginVM.createUsuario(email, password, nombre){
                        navController.navigate("Home")
                    }
                },modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                ) {
                    Text(text = "Registrarse")
                }

                if(LoginVM.showAlert){
                    Alert(
                        title = "Alerta",
                        message = "Usuario no Creado",
                        confirmText = "Aceptar",
                        onConfirmClick = { LoginVM.closeAlert() }){}
                }


            }
        }

    }
}