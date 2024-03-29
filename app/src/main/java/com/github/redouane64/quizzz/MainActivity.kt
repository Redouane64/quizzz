package com.github.redouane64.quizzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.redouane64.quizzz.views.MainView
import com.github.redouane64.quizzz.controllers.MainController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    override fun start() {
        mainController.start(this);
    }

    private val mainController : MainController = MainController();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton.setOnClickListener {
            // Start Questionnaire activity.
            this.start();

            // Clear current activity from activities back stack.
            finish();
        }

        exitButton.setOnClickListener { MainView.exit(this); }
    }

}
