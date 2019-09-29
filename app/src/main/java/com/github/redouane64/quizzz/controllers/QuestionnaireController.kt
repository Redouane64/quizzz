package com.github.redouane64.quizzz.controllers

import android.app.Activity
import android.content.Intent
import com.github.redouane64.quizzz.MainActivity
import com.github.redouane64.quizzz.ResultActivity
import com.github.redouane64.quizzz.views.QuestionnaireView
import com.github.redouane64.quizzz.models.Question

class QuestionnaireController(private val view: QuestionnaireView) {

    // This is hard coded questions list, it is static so it can be accessed
    // from different places in code. It can be designed differently, for example:
    // read from a JSON file or retrieved from RESTful API.
    // But for purpose of our application, static and hard coded list is okay.
    private val questions = arrayListOf(
        Question("What is love?", arrayOf("Baby", "Don't Hurt Me", "No More"), 0),
        Question("Do you like Android", arrayOf("Yes, Goddamn It", "Hell No", "Sabaka"), 1),
        Question("My name is Jeff?", arrayOf("Yes", "No", "Yes"), 2),
        Question("Do you know the way?", arrayOf("Potato", "Beaver", "Skunk"), 1),
        Question("What do you want?", arrayOf("Ice Cream", "SpongeBob", "Dunk"), 0)
    );

    // Index to track which question is currently displayed.
    private var currentQuestionIndex = 0;

    // helper function to increment current question, i.e move index to the next question.
    fun nextQuestion() = ++currentQuestionIndex;

    // helper function to move index of current question back, i.e back to previous question.
    fun previousQuestion() : Int? {

        if(--this.currentQuestionIndex < 0) return -1;

        val previousQuestion = this.getCurrentQuestion();

        renderQuestion(previousQuestion);

        return previousQuestion.selectedAnswer;
    }

    // Return current Question object.
    fun getCurrentQuestion() : Question = this.questions[this.currentQuestionIndex];

    // Check if there is a next question.
    // It used before to test before call to nextQuestion() method.
    fun hasNextQuestion() = this.currentQuestionIndex < questions.size - 1;

    fun currentQuestionHasAnswer() = this.getCurrentQuestion().selectedAnswer != null;

    // Set an answer value to the current question.
    fun setAnswer(index: Int) {
        this.getCurrentQuestion().selectedAnswer = index
    };

    // Start Result activity.
    fun finish(activity: Activity) {
        val resultActivityIntent = Intent(activity, ResultActivity::class.java);

        val correctAnswers = questions.filter { q -> q.selectedAnswer == q.correctAnswer }.size;
        val score = correctAnswers.toDouble() / questions.size.toDouble() * 100

        resultActivityIntent.putExtra("CORRECT_ANSWERS", correctAnswers)
        resultActivityIntent.putExtra("SCORE", score)

        activity.startActivity(resultActivityIntent);
    }

    // Update UI with current question text and options.
    fun renderQuestion(question: Question) {
        this.view.setQuestion(question.text);
        this.view.setOptions(question.choices);
    }

    // Navigate to main screen.
    // This is called when the current question is the first, so clicking back
    // takes you to the Main Activity (starting page).
    fun exitToMainScreen(context: Activity) {


        val mainScreenIntent = Intent(context, MainActivity::class.java);
        context.startActivity(mainScreenIntent);
    }
}