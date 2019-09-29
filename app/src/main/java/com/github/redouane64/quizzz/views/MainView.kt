package com.github.redouane64.quizzz.views

import android.app.Activity
import kotlin.system.exitProcess

interface MainView {

    fun start();

    // Finish
    companion object {
        fun exit(context: Activity): Unit = context.finishAndRemoveTask()
    }
}