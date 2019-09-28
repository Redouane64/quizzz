package com.github.redouane64.quizzz.Views

interface QuestionnaireView {

    fun nextQuestion();

    fun setQuestion(question: String);

    fun setOptions(options: Array<String>);

    fun selectOption();

    fun finish();

}