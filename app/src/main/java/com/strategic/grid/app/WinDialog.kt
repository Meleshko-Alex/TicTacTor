package com.strategic.grid.app

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView

class WinDialog(
    context: Context,
    private val message: String,
    private val activity: MainActivity
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_win)

        val messageTextView = findViewById<TextView>(R.id.tv_message)
        messageTextView.text = message

        val startAgainTextView = findViewById<TextView>(R.id.tv_start_again)
        startAgainTextView.setOnClickListener {
            activity.restartMatch()
            dismiss()
        }
    }
}