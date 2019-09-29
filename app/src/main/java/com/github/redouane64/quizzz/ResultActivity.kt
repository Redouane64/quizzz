package com.github.redouane64.quizzz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.redouane64.quizzz.controllers.ResultController
import com.github.redouane64.quizzz.models.Question
import com.github.redouane64.quizzz.views.MainView
import com.github.redouane64.quizzz.views.ResultView
import kotlinx.android.synthetic.main.activity_result.*
import kotlin.math.ceil

class ResultActivity : AppCompatActivity(), ResultView {

    private val resultController = ResultController(this);

    override fun restart() {
        resultController.restart(this);
    }

    override fun setScoreText(value: String) {
        resultText.text = value;
    }

    @SuppressLint("SetTextI18n")
    override fun setScore(value: Double) {
        resultScoreText.text = "${"%.0f".format(ceil(value))} %";
    }

    @SuppressLint("SetTextI18n")
    override fun setCorrectAnswers(value: Int) {
        correctAnswersText.text = "$value ${resources.getString(R.string.correct_answers)}";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var score = intent.getDoubleExtra("SCORE", 0.0);
        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0);

        exitButton.setOnClickListener {
            // Clear saved answers.
            // this.resultController.clearAnswers();

            // Exit the entire app.
            MainView.exit(this)
        }

        restartButton.setOnClickListener {
            // Go back to question list.
            restart();
            // Finish this activity from the back stack.
            finish();
        }

        setScore(score);
        setScoreText(resources.getString(this.resultController.getScoreText(score)));
        setCorrectAnswers(correctAnswers);
    }
}
