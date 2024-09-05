import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavExample() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, selectedItem) { index ->
                selectedItem = index
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") { LoginScreen() }
            composable("register") { RegisterScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, selectedItem: Int, onItemSelected: (Int) -> Unit) {
    
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.ExitToApp, contentDescription = "Login") },
            label = { Text("Login") },
            selected = selectedItem == 0,
            onClick = {
                onItemSelected(0)
                navController.navigate("login") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Register") },
            label = { Text("Register") },
            selected = selectedItem == 1,
            onClick = {
                onItemSelected(1)
                navController.navigate("register") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}

@Composable
fun LoginScreen() {
    // Aquí puedes agregar la UI del login
    Text("Login Screen", modifier = Modifier.fillMaxSize())
}

@Composable
fun RegisterScreen() {
    // Aquí puedes agregar la UI del registro
    Text("Register Screen", modifier = Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavExample() {
    BottomNavExample()
}
