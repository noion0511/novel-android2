package com.team3.showbee.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.team3.showbee.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
    }
}