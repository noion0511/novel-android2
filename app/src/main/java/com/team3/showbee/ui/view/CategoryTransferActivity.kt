package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.team3.showbee.R
import com.team3.showbee.data.entity.categoryMoneyList

class CategoryTransferActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_transfer)

        val moveToAdd = Intent(this, AddIncomeExpenditureActivity::class.java)
        val transferLayout = findViewById<LinearLayout>(R.id.transferLayout)

        val imageLayoutParams = LinearLayout.LayoutParams(
            100,
            100
        )

        for(i in 0 until categoryMoneyList.size) {
            var image: ImageView = ImageView(this)
            image.setImageResource((categoryMoneyList[i].second))
            image.tag = categoryMoneyList[i].first
            image.layoutParams = imageLayoutParams
            transferLayout.addView(image)

            image.setOnClickListener {
                moveToAdd.putExtra("icon", "${image.tag}")
                startActivity(moveToAdd)
            }
        }
    }
}