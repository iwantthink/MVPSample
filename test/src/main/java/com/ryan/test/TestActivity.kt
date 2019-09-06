package com.ryan.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject
import javax.inject.Named

class TestActivity : AppCompatActivity() {

    @Inject
    @field:com.ryan.test.TestQualifier("test")
    lateinit var name: String


    @Inject
    lateinit var stu: com.ryan.test.Student

    @Inject
    @Named("b")
    lateinit var lastName: String

    @Inject
    lateinit var pair: Pair<String, Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        DaggerStudentComponent.builder()
            .build()
            .inject(this)
    }
}
