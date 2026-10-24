package com.example.homework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.homework.ui.theme.TugasTheme
import com.example.homework.view.ExpenseScreen
import com.example.homework.view.HomeScreen
import com.example.homework.view.PhotoBackground
import com.example.homework.view.QuizScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasTheme {
                PhotoBackground {
                    AppContent()
                }
            }
        }
    }
}

@Composable
private fun AppContent() {
    var page by rememberSaveable { mutableStateOf("home") }
    BackHandler(enabled = page != "home") { page = "home" }
    when (page) {
        "expenses" -> ExpenseScreen()
        "quiz" -> QuizScreen()
        else -> HomeScreen(
            onExpenses = { page = "expenses" },
            onQuiz = { page = "quiz" }
        )
    }
}