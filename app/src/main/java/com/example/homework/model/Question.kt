package com.example.homework.model


data class Question(
    val text: String,
    val options: List<String>,
    val answer: String
)


data class Wrong(
    val number: Int,
    val question: String,
    val yourAnswer: String,
    val correctAnswer: String
)