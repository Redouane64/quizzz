package com.github.redouane64.quizzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.redouane64.quizzz.Views.QuestionnaireView
import com.github.redouane64.quizzz.controllers.QuestionnaireController
import kotlinx.android.synthetic.main.activity_questionnaire.*

class QuestionnaireActivity : AppCompatActivity(), QuestionnaireView {

    override fun setQuestion(question: String) {
        questionText.text = question;
    }

    override fun setOptions(options: Array<String>) {

        firstOptionRadioButton.text = options[0];
        secondOptionRadioButton.text = options[1];
        thirdOptionRadioButton.text = options[2];

    }

    override fun nextQuestion() {

    }

    override fun finish() {

    }

    val questionnaireController : QuestionnaireController = QuestionnaireController(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        val question = questionnaireController.getCurrentQuestion();
        questionnaireController.renderQuestion(question);
    }
}
