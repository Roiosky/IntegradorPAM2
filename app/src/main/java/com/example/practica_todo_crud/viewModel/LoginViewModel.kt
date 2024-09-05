package com.example.practica_todo_crud.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica_todo_crud.model.Usuario
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.example.practica_todo_crud.helpers.BiometricHelper

class LoginViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    var showAlert by mutableStateOf(false)
    var isBiometricEnabled by mutableStateOf(false)

    // Función para iniciar sesión con correo y contraseña
    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess()
                        } else {
                            Log.d("ERROR EN FIREBASE", "Usuario y Contraseña Incorrectos")
                            showAlert = true
                        }
                    }
            } catch (e: Exception) {
                Log.d("ERROR en JETPACK", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    // Función para crear un nuevo usuario
    fun createUsuario(email: String, password: String, nombre: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUser(nombre)
                            onSuccess()
                        } else {
                            Log.d("ERROR EN FIREBASE", "Error al crear Usuario")
                            showAlert = true
                        }
                    }
            } catch (e: Exception) {
                Log.d("ERROR en JETPACK", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    // Guardar datos del usuario en Firestore
    private fun saveUser(nombre: String) {
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch(Dispatchers.IO) {
            val userData = Usuario(
                userId = id ?: "",
                email = email ?: "",
                nombre = nombre
            )

            FirebaseFirestore.getInstance().collection("UsuariosTarea")
                .add(userData)
                .addOnSuccessListener {
                    Log.d("GUARDO", "Guardo Correctamente")
                }.addOnFailureListener {
                    Log.d("Error al guardar", "ERROR al guardar en firestore")
                }
        }
    }

    // Autenticación biométrica
    fun loginWithBiometrics(
        context: Context,
        activity: FragmentActivity,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        if (BiometricHelper.canAuthenticateWithBiometrics(context)) {
            val biometricPrompt = BiometricHelper.createBiometricPrompt(activity, onSuccess, onFailure)
            val promptInfo = BiometricHelper.buildPromptInfo()
            biometricPrompt.authenticate(promptInfo)
        } else {
            onFailure()
        }
    }

    // Cerrar alerta
    fun closeAlert() {
        showAlert = false
    }

    // Activar biometría
    fun enableBiometrics() {
        isBiometricEnabled = true
    }
}