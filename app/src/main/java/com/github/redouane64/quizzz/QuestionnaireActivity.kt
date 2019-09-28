package com.github.redouane64.quizzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.redouane64.quizzz.Views.QuestionnaireView
import com.github.redouane64.quizzz.controllers.QuestionnaireController
import kotlinx.android.synthetic.main.activity_questionnaire.*
import android.widget.RadioButton


class QuestionnaireActivity : AppCompatActivity(), QuestionnaireView {

    override fun selectOption() {

        val radioButtonID = optionsRadioGroup.checkedRadioButtonId
        val radioButton = optionsRadioGroup.findViewById<RadioButton>(radioButtonID)
        val index = optionsRadioGroup.indexOfChild(radioButton)

        questionnaireController.setAnswer(index);
    }

    override fun setQuestion(question: String) {
        questionText.text = question;
    }

    override fun setOptions(options: Array<String>) {

        firstOptionRadioButton.text = options[0];
        secondOptionRadioButton.text = options[1];
        thirdOptionRadioButton.text = options[2];

    }

    override fun nextQuestion() {

        if(questionnaireController.currentQuestionHasAnswer()) {
            // TODO:
            return;
        }

        // TODO: Show toast says that the user has to choose an answer in order to proceed.
    }

    override fun finish() {

    }

    private val questionnaireController : QuestionnaireController = QuestionnaireController(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        val question = questionnaireController.getCurrentQuestion();
        questionnaireController.renderQuestion(question);

        optionsRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            selectOption();
        }
    }
}
