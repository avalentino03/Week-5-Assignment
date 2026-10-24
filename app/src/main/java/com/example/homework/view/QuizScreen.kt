package com.example.homework.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework.ui.theme.Brown
import com.example.homework.ui.theme.BrownDark
import com.example.homework.ui.theme.ErrorRed
import com.example.homework.ui.theme.Tan
import com.example.homework.viewmodel.QuizViewModel
@Composable
fun QuizScreen(vm: QuizViewModel = viewModel()) {
    if (vm.finished) ResultContent(vm) else QuestionContent(vm)
}
@Composable
private fun QuestionContent(vm: QuizViewModel) {
    val question = vm.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Question ${vm.index + 1}",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = question.text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        question.options.forEach { option ->
            val isSelected = vm.selected == option
            Button(
                onClick = { vm.select(option) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Brown else Tan,
                    contentColor = if (isSelected) Color.White else BrownDark
                )
            ) {
                Text(option)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = vm::next,
            enabled = vm.selected != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}
@Composable
private fun ResultContent(vm: QuizViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "quiz finished", style = MaterialTheme.typography.titleLarge)
        Text(text = "Score mu: ${vm.score} / ${vm.questions.size}")
        Spacer(modifier = Modifier.height(16.dp))
        if (vm.wrongAnswers.isEmpty()) {
            Text(text = "no wrong answers mantabs.", color = Brown)
        } else {
            vm.wrongAnswers.forEach { wrong ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Q${wrong.number}: ${wrong.question}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "your answer:${wrong.yourAnswer}",
                        style = MaterialTheme.typography.bodySmall,
                        color = ErrorRed
                    )
                    Text(
                        text = "correct answer: ${wrong.correctAnswer}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Brown
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = vm::start, modifier = Modifier.fillMaxWidth()) {
            Text("restart")
        }
    }
}