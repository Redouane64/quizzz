package com.github.redouane64.quizzz.views

interface QuestionnaireView {

    fun nextQuestion();

    fun setQuestion(question: String);

    fun setOptions(options: Array<String>);

    fun selectOption();

    fun finish();

}