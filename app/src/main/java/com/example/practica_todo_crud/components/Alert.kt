package com.example.practica_todo_crud.components

import android.app.AlertDialog
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Alert(
    title: String,
    message: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit // Añadido parámetro para el dismiss
) {
    AlertDialog(
        onDismissRequest = onDismissClick, // Se ejecuta cuando la alerta se dismita
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            Button(onClick = onConfirmClick) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            Button(onClick = onDismissClick) {
                Text(text = "Cancelar")
            }
        }
    )
}
