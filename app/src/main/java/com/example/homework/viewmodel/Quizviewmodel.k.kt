package com.example.homework.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.homework.model.Question
import com.example.homework.model.Wrong

class QuizViewModel : ViewModel() {
    private val questionBank = listOf(
        Question(
            "bahasa program utama untuk Android modern?",
            listOf("Kotlin", "Swift", "PHP", "Ruby"),
            "Kotlin"
        ),
        Question(
            "Library UI deklaratif dari Google untuk Android?",
            listOf("Flutter", "Jetpack Compose", "React", "Bootstrap"),
            "Jetpack Compose"
        ),
        Question(
            "Pada MVVM, bagian yang menyimpan state dan logika adalah?",
            listOf("Model", "View", "ViewModel", "Manifest"),
            "ViewModel"
        ),
        Question(
            "IDE resmi untuk membuat aplikasi Android?",
            listOf("Android Studio", "Xcode", "Eclipse", "Sublime Text"),
            "Android Studio"
        ),
        Question(
            "Ibu kota Indonesia adalah?",
            listOf("Surabaya", "Bandung", "Jakarta", "Medan"),
            "Jakarta"
        ),
        Question(
            "Komponen Compose untuk menampilkan teks?",
            listOf("Label", "TextView", "Text", "Paragraph"),
            "Text"
        )
    )

    private val quizSize = 3
    var questions by mutableStateOf<List<Question>>(emptyList())
        private set
    var index by mutableStateOf(0)
        private set
    var selected by mutableStateOf<String?>(null)
        private set
    var score by mutableStateOf(0)
        private set
    var finished by mutableStateOf(false)
        private set

    val wrongAnswers = mutableStateListOf<Wrong>()

    val current: Question get() = questions[index]

    init {
        start()
    }

    // restart
    fun start() {
        questions = questionBank.shuffled().take(quizSize)
        index = 0
        selected = null
        score = 0
        finished = false
        wrongAnswers.clear()
    }
    fun select(option: String) {
        selected = option
    }
    fun next() {
        val answer = selected ?: return
        if (answer == current.answer) {
            score++
        } else {
            wrongAnswers.add(Wrong(index + 1, current.text, answer, current.answer))
        }
        if (index == questions.lastIndex) {
            finished = true
        } else {
            index++
            selected = null
        }
    }
}