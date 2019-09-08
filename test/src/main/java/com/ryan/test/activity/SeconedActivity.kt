package com.ryan.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ryan.test.DaggerBirdComponent
import com.ryan.test.R

class SeconedActivity : AppCompatActivity() {
//
//    @Inject
//    lateinit var bird: Bird

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seconed)

        DaggerBirdComponent.create().inject(this)

//        log<SeconedActivity>("bird = $bird")
    }
}
