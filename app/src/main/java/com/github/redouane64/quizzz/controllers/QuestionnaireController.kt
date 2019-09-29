package com.github.redouane64.quizzz.controllers

import android.app.Activity
import android.content.Intent
import com.github.redouane64.quizzz.MainActivity
import com.github.redouane64.quizzz.ResultActivity
import com.github.redouane64.quizzz.views.QuestionnaireView
import com.github.redouane64.quizzz.models.Question

class QuestionnaireController(private val view: QuestionnaireView) {

    companion object {

        public val questions = arrayListOf(
            Question("Question 1", arrayOf("Option 1", "Option 2", "Option 3"), 0),
            Question("Question 2", arrayOf("Option 1", "Option 2", "Option 3"), 0),
            Question("Question 3", arrayOf("Option 1", "Option 2", "Option 3"), 0)
        );

    }
    private var currentQuestionIndex = 0;

    fun nextQuestion() = ++currentQuestionIndex;

    fun previousQuestion() : Int? {

        if(--this.currentQuestionIndex < 0) return -1;

        val previousQuestion = this.getCurrentQuestion();

        renderQuestion(previousQuestion);

        return previousQuestion.selectedAnswer;
    }

    fun getCurrentQuestion() : Question = QuestionnaireController.questions[this.currentQuestionIndex];

    fun hasNextQuestion() = this.currentQuestionIndex < questions.size - 1;

    fun currentQuestionHasAnswer() = this.getCurrentQuestion().selectedAnswer != null;

    fun setAnswer(index: Int) {
        this.getCurrentQuestion().selectedAnswer = index
    };

    fun finish(activity: Activity) {
        val resultActivityIntent = Intent(activity, ResultActivity::class.java);
        activity.startActivity(resultActivityIntent);
    }

    fun renderQuestion(question: Question) {
        this.view.setQuestion(question.text);
        this.view.setOptions(question.choices);
    }

    fun exitToMainScreen(context: Activity) {
        val mainScreenIntent = Intent(context, MainActivity::class.java);
        context.startActivity(mainScreenIntent);
    }
}