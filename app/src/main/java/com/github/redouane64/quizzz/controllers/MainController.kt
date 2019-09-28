package com.github.redouane64.quizzz.controllers

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.github.redouane64.quizzz.QuestionnaireActivity

class MainController {

    fun start(activity : Activity) {

        Log.d(this.toString(),"Start method executed.");

        val questionnaireIntent = Intent(activity, QuestionnaireActivity::class.java);
        activity.startActivity(questionnaireIntent);
    }
}