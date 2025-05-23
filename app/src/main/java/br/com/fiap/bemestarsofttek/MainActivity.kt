package br.com.fiap.bemestarsofttek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bemestarsofttek.components.Footer
import br.com.fiap.bemestarsofttek.components.Header
import br.com.fiap.bemestarsofttek.navigation.BemEstarNavigation
import br.com.fiap.bemestarsofttek.navigation.Screen
import br.com.fiap.bemestarsofttek.ui.theme.BemEstarSofttekTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BemEstarSofttekTheme {
                BemEstarApp()
            }
        }
    }
}

@Composable
fun BemEstarApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Dashboard.route
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Header(
                currentRoute = currentRoute,
                navController = navController
            )
        },
        bottomBar = {
            Footer()
        }
    ) { innerPadding ->
        BemEstarNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}