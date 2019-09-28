package com.github.redouane64.quizzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.redouane64.quizzz.controllers.QuestionnaireController

class QuestionnaireActivity : AppCompatActivity() {

    val questionnaireController : QuestionnaireController = QuestionnaireController(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)
    }
}
