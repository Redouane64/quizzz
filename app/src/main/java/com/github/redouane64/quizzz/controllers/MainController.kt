package com.github.redouane64.quizzz.controllers

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.github.redouane64.quizzz.QuestionnaireActivity

class MainController {

    // Start Questionnaire activity.
    fun start(context : Activity) {

        // Log some crap.
        Log.d(this.toString(),"Start method executed.");

        val questionnaireIntent = Intent(context, QuestionnaireActivity::class.java);
        context.startActivity(questionnaireIntent);
    }
}