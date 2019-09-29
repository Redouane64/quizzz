package com.github.redouane64.quizzz

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.redouane64.quizzz.controllers.QuestionnaireController
import com.github.redouane64.quizzz.views.QuestionnaireView
import kotlinx.android.synthetic.main.activity_questionnaire.*


class QuestionnaireActivity : AppCompatActivity(), QuestionnaireView {

    private fun checkAnswerAtIndex(index: Int?) {
        when (index) {
            0 -> optionsRadioGroup.check(firstOptionRadioButton.id);
            1 -> optionsRadioGroup.check(secondOptionRadioButton.id);
            2 -> optionsRadioGroup.check(thirdOptionRadioButton.id);
        }
    }

    private fun getCheckedAnswerIndex() : Int? {
        return when {
            firstOptionRadioButton.isChecked -> 0
            secondOptionRadioButton.isChecked -> 1
            thirdOptionRadioButton.isChecked -> 2
            else -> null
        };
    }

    override fun setQuestion(question: String) {
        questionText.text = question;
    }

    override fun setOptions(options: Array<String>) {

        firstOptionRadioButton.text = options[0];
        secondOptionRadioButton.text = options[1];
        thirdOptionRadioButton.text = options[2];

    }

    override fun previousQuestion() {

        when (questionnaireController.previousQuestion()) {

            -1 -> questionnaireController.exitToMainScreen(this);

            0 -> optionsRadioGroup.check(firstOptionRadioButton.id);
            1 -> optionsRadioGroup.check(secondOptionRadioButton.id);
            2 -> optionsRadioGroup.check(thirdOptionRadioButton.id);
        }
    }

    override fun nextQuestion() {

        val checkAnswer = this.getCheckedAnswerIndex();

        if(checkAnswer != null) {

            questionnaireController.setAnswer(checkAnswer);

            if(questionnaireController.hasNextQuestion()) {
                questionnaireController.nextQuestion();

                optionsRadioGroup.clearCheck();
                val nextQuestion = questionnaireController.getCurrentQuestion();
                questionnaireController.renderQuestion(nextQuestion);

                if(nextQuestion.selectedAnswer != null) {
                    this.checkAnswerAtIndex(nextQuestion.selectedAnswer);
                }

            } else {
                finish();
            }

            return;
        }

        Toast.makeText(this, R.string.must_choose_answer, Toast.LENGTH_LONG).show();
    }

    override fun finish() {
        questionnaireController.finish(this);
    }

    private val questionnaireController : QuestionnaireController = QuestionnaireController(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        val question = questionnaireController.getCurrentQuestion();
        questionnaireController.renderQuestion(question);

        nextQuestionButton.setOnClickListener {
            this.nextQuestion();
        }

        backButton.setOnClickListener { this.previousQuestion(); }
    }
}
