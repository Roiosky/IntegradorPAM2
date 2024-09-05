package com.example.practica_todo_crud.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.practica_todo_crud.model.Tareas
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class TareasViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore

    //private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    //private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _tareaData = MutableStateFlow<List<Tareas>>(emptyList())
    val tareaData: StateFlow<List<Tareas>> = _tareaData

    var state: Tareas by mutableStateOf(Tareas())
        private set



    fun onValue(value: String, text: String){
        when(text){
            "titulo" -> state = state.copy(titulo = value)
            "desc" -> state = state.copy(desc = value)
            "estado" -> state = state.copy(estado = value.toBoolean())
        }
    }

    // funcion para mostrar todas las tareas
    fun getAllTareas() {
        val email = auth.currentUser?.email

        firestore.collection("Tareas")
            .whereEqualTo("emailUser", email.toString())
            .addSnapshotListener {querySnapshot, error ->
                if (error != null ) {
                    return@addSnapshotListener
                }

                val documents = mutableListOf<Tareas>()
                if (querySnapshot != null){
                    for (document in querySnapshot) {
                        val myDocument = document.toObject(Tareas::class.java).copy(idTarea = document.id)
                        documents.add(myDocument)
                    }
                }
                _tareaData.value = documents
            }
    }


    // funcion para traer una tarea por el id
    fun getTareaById(documentId: String) {
        firestore.collection("Tareas")
            .document(documentId)
            .addSnapshotListener{ snapshot, _ ->
                if (snapshot != null) {
                    val tarea = snapshot.toObject(Tareas::class.java)
                    state = state.copy(
                        // elemento a editar
                        titulo = tarea?.titulo ?: "",
                        desc = tarea?.desc ?: "",
                        estado = tarea?.estado ?: false
                    )
                }
            }
    }


    // funcion para crear/fuardar nueva tarea
    fun saveNewTarea(
        titulo: String,
        desc: String,
        estado: Boolean,
        onSuccess: () -> Unit
    ) {
        val email = auth.currentUser?.email

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Obtener los datos
                val NewTarea = hashMapOf(
                    "titulo" to titulo,
                    "desc" to desc,
                    "estado" to estado,
                    "emailUser" to email.toString()
                )

                // guardar
                firestore.collection("Tareas")
                    .add(NewTarea)
                    .addOnSuccessListener {
                        Log.d("SAVE SUCCESS", "Tarea guardada correctamente: ${it.id}")
                        onSuccess()
                    } .addOnFailureListener { e ->
                        Log.d("ERROR SAVE", "Error al guardar ${e.localizedMessage}")
                    }

            } catch (e: Exception) {
                Log.d("ERROR SAVE", "Error al guardar ${e.localizedMessage}")
            }
        }
    }


    // funcion para actualizar tareas
    fun updateTarea(idTarea: String, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val editTarea = hashMapOf(
                    "titulo" to state.titulo,
                    "desc" to state.desc,
                    "estado" to state.estado
                )
                firestore.collection("Tareas").document(idTarea)
                    .update(editTarea as Map<String, Any>)
                    .addOnSuccessListener {
                        onSuccess()
                    }
            } catch (e:Exception){
                Log.d("ERROR UPDATE","Error al Actualziar ${e.localizedMessage} ")
            }
        }
    }

    // funcion para eliminar
    fun deleteTarea(idTarea: String, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                firestore.collection("Tareas").document(idTarea)
                    .delete()
                    .addOnSuccessListener {
                        onSuccess()
                    }
            } catch (e: Exception) {
                Log.d("ERROR DELETE", "Error al eliminar la Tareas ${e.localizedMessage}" )
            }
        }
    }



    // funcion para cerrar session
    fun signOut() {
        auth.signOut()
    }

}