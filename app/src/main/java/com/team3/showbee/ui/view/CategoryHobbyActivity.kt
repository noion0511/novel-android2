package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.team3.showbee.R
import com.team3.showbee.data.entity.categoryBookList
import com.team3.showbee.data.entity.categoryExerciseList
import com.team3.showbee.data.entity.categoryMusicList
import com.team3.showbee.data.entity.categoryOttList

class CategoryHobbyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_hobby)

        val moveToAdd = Intent(this, AddIncomeExpenditureActivity::class.java)
        val ottLayout = findViewById<LinearLayout>(R.id.ottLayout)
        val musicLayout = findViewById<LinearLayout>(R.id.musicLayout)
        val bookLayout = findViewById<LinearLayout>(R.id.bookLayout)
        val exerciseLayout = findViewById<LinearLayout>(R.id.exerciseLayout)

        val imageLayoutParams = LinearLayout.LayoutParams(
            100,
            100
        )

        for(i in 0 until categoryOttList.size) {
            var image: ImageView = ImageView(this)
            image.setImageResource((categoryOttList[i].second))
            image.tag = categoryOttList[i].first
            image.layoutParams = imageLayoutParams
            ottLayout.addView(image)

            image.setOnClickListener {
                moveToAdd.putExtra("icon", "${image.tag}")
                startActivity(moveToAdd)
            }
        }
        for(i in 0 until categoryMusicList.size) {
            var image: ImageView = ImageView(this)
            image.setImageResource((categoryMusicList[i].second))
            image.tag = categoryMusicList[i].first
            image.layoutParams = imageLayoutParams
            musicLayout.addView(image)

            image.setOnClickListener {
                moveToAdd.putExtra("icon", "${image.tag}")
                startActivity(moveToAdd)
            }
        }
        for(i in 0 until categoryBookList.size) {
            var image: ImageView = ImageView(this)
            image.setImageResource((categoryBookList[i].second))
            image.tag = categoryBookList[i].first
            image.layoutParams = imageLayoutParams
            bookLayout.addView(image)

            image.setOnClickListener {
                moveToAdd.putExtra("icon", "${image.tag}")
                startActivity(moveToAdd)
            }
        }
        for(i in 0 until categoryExerciseList.size) {
            var image: ImageView = ImageView(this)
            image.setImageResource((categoryExerciseList[i].second))
            image.tag = categoryExerciseList[i].first
            image.layoutParams = imageLayoutParams
            exerciseLayout.addView(image)

            image.setOnClickListener {
                moveToAdd.putExtra("icon", "${image.tag}")
                startActivity(moveToAdd)
            }
        }

    }
}