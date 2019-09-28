package com.github.redouane64.quizzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.redouane64.quizzz.views.QuestionnaireView
import com.github.redouane64.quizzz.controllers.QuestionnaireController
import kotlinx.android.synthetic.main.activity_questionnaire.*
import android.widget.RadioButton
import android.widget.Toast


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

            optionsRadioGroup.clearCheck();

            // TODO:
            questionnaireController.nextQuestion();
            questionnaireController.renderQuestion(questionnaireController.getCurrentQuestion());

            return;
        }

        // TODO: Show toast says that the user has to choose an answer in order to proceed.
        Toast.makeText(this, R.string.must_choose_answer, Toast.LENGTH_LONG).show();
    }

    override fun finish() {

    }

    private val questionnaireController : QuestionnaireController = QuestionnaireController(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        val question = questionnaireController.getCurrentQuestion();
        questionnaireController.renderQuestion(question);

        optionsRadioGroup.setOnCheckedChangeListener { _, _ ->
            selectOption();
        }

        nextQuestionButton.setOnClickListener {
            nextQuestion();
        }
    }
}
