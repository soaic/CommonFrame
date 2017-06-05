package com.commonframe.kotlin

import android.app.Activity
import android.os.Bundle
import com.commonframe.R
import com.commonlibrary.ToastUtils
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        test_kotlin_txt.text = "测试"

        test_kotlin_txt.setOnClickListener {
            ToastUtils.showLongToast(this,"ceshi")
        }
    }
    
    
}
