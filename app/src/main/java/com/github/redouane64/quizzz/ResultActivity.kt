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

    override fun setCorrectAnswers(value: Int) {
        correctAnswersText.text = value.toString();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        exitButton.setOnClickListener {

            // Exit the entire app.
            MainView.exit(this)
        }

        restartButton.setOnClickListener {

            // Finish this activity from the back stack.
            finish();

            // Go back to question list.
            restart();
        }

        setScore(this.resultController.getScore());
        setScoreText(resources.getString(this.resultController.getScoreText()));
        setCorrectAnswers(this.resultController.getCorrectAnswers());
    }
}
