package com.github.redouane64.quizzz.models

class Question (val text: String, val choices: Array<String>, val correctAnswer: Int?) {
    var selectedAnswer: Int? = null;
}