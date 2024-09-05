package com.example.practica_todo_crud.model

data class Tareas(
    val idTarea: String = "",
    val titulo: String = "",
    val desc: String = "",
    val estado: Boolean = false,
    val emailUser: String = "",
)
