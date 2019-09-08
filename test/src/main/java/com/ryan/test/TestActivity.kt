package com.ryan.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ryan.test.activity.SeconedActivity
import com.ryan.test.dog.Creature
import com.ryan.test.dog.Dog
import com.ryan.test.dog.Pig
import javax.inject.Inject
import javax.inject.Named

inline fun <reified T> Activity.log(msg: String) {
    Log.e(T::class.java.simpleName, msg)
}

class TestActivity : AppCompatActivity() {

    @Inject
    @field:TestQualifier("test")
    lateinit var testStr: String

    @Inject
    @field: Named("lastName")
    lateinit var lastName: String

    @Inject
    lateinit var stu: Student

    /**
     * 如果这里是Dog的类型为抽象类,并且使用@Inject提供注入类，那么dagger将报错
     * 只能通过提供Module来提供!!!!!并且Module中提供实例的方法的返回值需要是抽象类
     */
    @Inject
    lateinit var dog: Creature

    @Inject
    lateinit var dog2: Creature

    @Inject
    lateinit var pig1: Pig

    @Inject
    lateinit var pig2: Pig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        DaggerTestComponent.builder()
            .build()
            .inject(this)

        log<TestActivity>("testStr = $testStr")
        log<TestActivity>("lastName = $lastName")
        log<TestActivity>("stu = $stu")
        log<TestActivity>("stu.name = ${stu.name}")
        log<TestActivity>("stu.clazz = ${stu.clazz}")
        log<TestActivity>("dog = ${dog}")
        log<TestActivity>("dog2 = ${dog2}")

        log<TestActivity>("pig1 = ${pig1}")
        log<TestActivity>("pig2 = ${pig2}")

//        startActivity(Intent(this, SeconedActivity::class.java))

    }
}
