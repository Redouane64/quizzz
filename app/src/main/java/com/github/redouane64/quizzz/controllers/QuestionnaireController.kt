package com.github.redouane64.quizzz.controllers

import com.github.redouane64.quizzz.Views.QuestionnaireView
import com.github.redouane64.quizzz.models.Question

class QuestionnaireController(val view: QuestionnaireView) {

    private val questions = arrayListOf<Question>(
        Question("Question 1", arrayOf<String>("Option 1", "Option 2", "Option 3"), 0),
        Question("Question 2", arrayOf<String>("Option 1", "Option 2", "Option 3"), 0),
        Question("Question 3", arrayOf<String>("Option 1", "Option 2", "Option 3"), 0)
    );

    private var currentQuestionIndex = 0;

    fun nextQuestion() = ++currentQuestionIndex;

    fun getCurrentQuestion() : Question = this.questions[this.currentQuestionIndex];

    fun currentQuestionHasAnswer() = this.questions[currentQuestionIndex].selectedAnswer != null;

    fun setAnswer(index: Int) {
        this.getCurrentQuestion().selectedAnswer = index
    };

    fun renderQuestion(question: Question) {
        this.view.setQuestion(question.text);
        this.view.setOptions(question.choices);
    }
}