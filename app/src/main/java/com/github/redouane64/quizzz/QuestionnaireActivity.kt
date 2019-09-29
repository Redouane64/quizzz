package com.github.redouane64.quizzz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.redouane64.quizzz.controllers.QuestionnaireController
import com.github.redouane64.quizzz.views.QuestionnaireView
import kotlinx.android.synthetic.main.activity_questionnaire.*


class QuestionnaireActivity : AppCompatActivity(), QuestionnaireView {

    // Check the proper option RadioButton.
    // This is used when User navigate back to a previous question
    // to check the RadioButton with the previously selected option.
    private fun checkAnswerAtIndex(index: Int?) {
        when (index) {
            0 -> optionsRadioGroup.check(firstOptionRadioButton.id);
            1 -> optionsRadioGroup.check(secondOptionRadioButton.id);
            2 -> optionsRadioGroup.check(thirdOptionRadioButton.id);
        }
    }

    // Get the checked answer of the current Question
    // This is used when user navigate back to the previous
    // Question.
    private fun getCheckedAnswerIndex() : Int? {
        return when {
            firstOptionRadioButton.isChecked -> 0
            secondOptionRadioButton.isChecked -> 1
            thirdOptionRadioButton.isChecked -> 2
            else -> null
        };
    }

    // Update Question Text in the UI.
    override fun setQuestion(question: String) {
        questionText.text = question;
    }

    // Update quesiton options radio buttons text with Question options.
    override fun setOptions(options: Array<String>) {

        firstOptionRadioButton.text = options[0];
        secondOptionRadioButton.text = options[1];
        thirdOptionRadioButton.text = options[2];

    }

    // Move current question back and fetch the answer selected
    // by the user for the question and check the right RadioButton.
    override fun previousQuestion() {

        when (questionnaireController.previousQuestion()) {

            -1 -> {
                finish(); // remove this activity when going back to Main screen.
                questionnaireController.exitToMainScreen(this);
            }

            0 -> optionsRadioGroup.check(firstOptionRadioButton.id);
            1 -> optionsRadioGroup.check(secondOptionRadioButton.id);
            2 -> optionsRadioGroup.check(thirdOptionRadioButton.id);
        }
    }

    override fun nextQuestion() {

        val checkAnswer = this.getCheckedAnswerIndex();

        // Test if user has selected an answer.
        if(checkAnswer != null) {

            // Save selected answer to the current Question in
            // QuestionnaireController.questions list.
            questionnaireController.setAnswer(checkAnswer);

            // Test if there is a next question.
            if(questionnaireController.hasNextQuestion()) {
                questionnaireController.nextQuestion();

                optionsRadioGroup.clearCheck();
                val nextQuestion = questionnaireController.getCurrentQuestion();
                questionnaireController.renderQuestion(nextQuestion);

                if(nextQuestion.selectedAnswer != null) {
                    this.checkAnswerAtIndex(nextQuestion.selectedAnswer);
                }

            }
            // If there is no next question then navigate to Result activity.
            else {
                // this will navigate to Result activity and do necessary clean up.
                complete();
            }

            return; //
        }

        // Show a Toast notification if the user did not select an answer.
        Toast.makeText(this, R.string.must_choose_answer, Toast.LENGTH_LONG).show();
    }

    override fun complete() {

        // Navigate to Result activity.
        questionnaireController.finish(this);

        // Clear this activity from stack.
        // This is needed so user cannot go back to the Questions screen from Result screen.
        // It is only done by clicking the Restart button.
        finish();
    }

    private val questionnaireController : QuestionnaireController = QuestionnaireController(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        // Get current (first) question and display in screen.
        val question = questionnaireController.getCurrentQuestion();
        questionnaireController.renderQuestion(question);

        // Set click action to Next and Previous UI Buttons.
        nextQuestionButton.setOnClickListener { this.nextQuestion(); }
        exitButton.setOnClickListener {
            this.previousQuestion();
        }
    }
}
