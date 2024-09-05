package com.example.practica_todo_crud.views.Login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica_todo_crud.viewModel.LoginViewModel

@Composable
fun TabsView(navController: NavController, LoginVM: LoginViewModel){
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Iniciar Sessión", "Registrarse")


    Column(modifier = Modifier.padding(top = 30.dp)) {
        TabRow(selectedTabIndex = selectedTab,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                )
            }
        ) {
            tabs.forEachIndexed{index, title ->
                Tab(selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(text = title) }
                )
            }
        }
        ///
        when(selectedTab) {
            0 -> LoginView(navController, LoginVM)
            1 -> RegisterView(navController, LoginVM)
        }
    }
}