package com.ryan.mvpsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mbtnJump.setOnClickListener {
            //            afterLogin {
//                toast("没有登录，前去登录页")
//            }
        }
    }
}
