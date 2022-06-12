package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.team3.showbee.R

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val chooseHobby = findViewById<TextView>(R.id.choose_hobby)
        val chooseLife = findViewById<TextView>(R.id.choose_life)
        val chooseTransfer = findViewById<TextView>(R.id.choose_transfer)

        val moveToH = Intent(this, CategoryHobbyActivity::class.java)
        val moveToT = Intent(this, CategoryTransferActivity::class.java)
        val moveToL = Intent(this, CategoryLifeActivity::class.java)

        chooseHobby.setOnClickListener {
            startActivity(moveToH)
        }

        chooseLife.setOnClickListener {
            startActivity(moveToT)
        }

        chooseTransfer.setOnClickListener {
            startActivity(moveToL)
        }

    }
}