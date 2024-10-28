package com.mobily.bugitapp.uiscreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class DashboardScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppWidget {
                NavAppGraph()
                println("Dashboard")
            }
        }
    }
}

@Composable
fun AppWidget(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface {
            content()
        }
    }
}
@Composable
fun DashboardUI(navController: NavController) {
    Spacer(modifier = Modifier.height(55.dp))
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
    Text(
        text = "Welcome to Bug Tracking System",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
    )
}
    Spacer(modifier = Modifier.height(15.dp))
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Button(onClick = { navController.navigate("addBug") }) {
            Text(text = "GO TO ADD BUG")
        }
    }
}
