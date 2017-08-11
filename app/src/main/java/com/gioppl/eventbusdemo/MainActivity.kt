package com.gioppl.eventbusdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    var ed_main: EditText? = null
    var btn_main: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this);//注册成为订阅者
        initView()

    }

    private fun initView() {
        ed_main = findViewById(R.id.ed_main) as EditText?
        btn_main = findViewById(R.id.btn_main) as Button?
        btn_main!!.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
            EventBus.getDefault().postSticky("这个是一个粘性事件");
        }
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onEvent(messageEvent: MessageEvent) {
        ed_main!!.setText(messageEvent.message)
    }

    override fun onDestroy() {
        super.onDestroy()
        //解除注册
        EventBus.getDefault().unregister(this)
    }

}
