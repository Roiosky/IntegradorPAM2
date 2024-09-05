package com.example.practica_todo_crud.model

data class Usuario(
    val userId: String,
    val nombre: String,
    val email: String,

) {
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "userId" to this.userId,
            "nombre" to this.nombre,
            "email" to this.email,
        )
    }
}
