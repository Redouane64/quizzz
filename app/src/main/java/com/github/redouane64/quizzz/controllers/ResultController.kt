package com.github.redouane64.quizzz.controllers

import android.app.Activity
import android.content.Intent
import com.github.redouane64.quizzz.QuestionnaireActivity
import com.github.redouane64.quizzz.R
import com.github.redouane64.quizzz.views.ResultView
import kotlin.math.ceil

class ResultController(val view : ResultView) {

    fun getScore() : Double {
        val correctAnswers = QuestionnaireController.questions.filter { q -> q.selectedAnswer == q.correctAnswer };

        return correctAnswers.size.toDouble() / QuestionnaireController.questions.size.toDouble() * 100
    }

    fun getScoreText() : Int = when(this.getScore()) {
        in 0..25 -> R.string.bad_score
        in 26..50 -> R.string.not_bad
        in 51..75 -> R.string.good_or_better_yet
        else -> R.string.excellent;
    }

    fun getCorrectAnswers() = QuestionnaireController.questions.filter { q -> q.selectedAnswer == q.correctAnswer }.size;

    fun restart(context: Activity) {

        // Clear sqved answers of questions list.
        this.clearAnswers()

        // Navigate to Question screen.
        val questionnaireActivityIntent = Intent(context, QuestionnaireActivity::class.java);
        context.startActivity(questionnaireActivityIntent);
    }

    fun clearAnswers() {
        for (q in QuestionnaireController.questions) {
            q.selectedAnswer = null;
        }
    }

}