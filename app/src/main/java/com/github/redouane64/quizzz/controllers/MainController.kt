package com.github.redouane64.quizzz.controllers

import android.content.Intent
import android.util.Log
import com.github.redouane64.quizzz.MainActivity
import com.github.redouane64.quizzz.QuestionnaireActivity

class MainController(view: MainActivity) {

    private var mainActivity: MainActivity = view;

    fun Start() {

        Log.d(this.toString(),"Start method executed.");

        val questionnaireIntent = Intent(mainActivity, QuestionnaireActivity::class.java);
        mainActivity.startActivity(questionnaireIntent);
    }
}