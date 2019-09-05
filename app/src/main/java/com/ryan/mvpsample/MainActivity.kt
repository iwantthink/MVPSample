package com.ryan.mvpsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ryan.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mbtnJump.setOnClickListener {
            afterLogin {
                toast("没有登录，前去登录页")
            }
        }
    }
}
