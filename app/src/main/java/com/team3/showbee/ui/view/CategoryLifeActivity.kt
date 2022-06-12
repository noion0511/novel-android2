package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.team3.showbee.R
import com.team3.showbee.data.entity.categoryHomeList
import com.team3.showbee.data.entity.categoryPhoneList

class CategoryLifeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_life)

        val moveToAdd = Intent(this, AddIncomeExpenditureActivity::class.java)
        val lifeLayout = findViewById<LinearLayout>(R.id.lifeLayout)
        val phoneLayout = findViewById<LinearLayout>(R.id.phoneLayout)

        val imageLayoutParams = LinearLayout.LayoutParams(
            100,
            100
        )

        for(i in 0 until categoryHomeList.size) {
            var image: ImageView = ImageView(this)
            image.setImageResource((categoryHomeList[i].second))
            image.tag = categoryHomeList[i].first
            image.layoutParams = imageLayoutParams
            lifeLayout.addView(image)

            image.setOnClickListener {
                moveToAdd.putExtra("icon", "${image.tag}")
                startActivity(moveToAdd)
            }
        }
        for(i in 0 until categoryPhoneList.size) {
            var image: ImageView = ImageView(this)
            image.setImageResource((categoryPhoneList[i].second))
            image.tag = categoryPhoneList[i].first
            image.layoutParams = imageLayoutParams
            phoneLayout.addView(image)

            image.setOnClickListener {
                moveToAdd.putExtra("icon", "${image.tag}")
                startActivity(moveToAdd)
            }
        }
    }
}