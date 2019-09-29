package com.github.redouane64.quizzz.views

interface ResultView {
    fun restart();

    fun setScoreText(value: String);
    fun setScore(value: Double);
    fun setCorrectAnswers(value: Int);
}