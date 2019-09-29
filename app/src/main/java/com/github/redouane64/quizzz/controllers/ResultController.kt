package com.github.redouane64.quizzz.controllers

import android.app.Activity
import android.content.Intent
import com.github.redouane64.quizzz.QuestionnaireActivity
import com.github.redouane64.quizzz.R
import com.github.redouane64.quizzz.views.ResultView
import kotlin.math.ceil

class ResultController(val view : ResultView) {

    fun getScoreText(score: Double) : Int = when(score) {
        in 0..25 -> R.string.bad_score
        in 26..50 -> R.string.not_bad
        in 51..75 -> R.string.good_or_better_yet
        else -> R.string.excellent;
    }

    fun restart(context: Activity) {

        // Navigate to Question screen.
        val questionnaireActivityIntent = Intent(context, QuestionnaireActivity::class.java);
        context.startActivity(questionnaireActivityIntent);
    }

}