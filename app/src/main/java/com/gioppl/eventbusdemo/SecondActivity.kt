package com.gioppl.eventbusdemo

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode




/**
 * Created by GIOPPL on 2017/8/10.
 */

class SecondActivity : Activity() {
    var tv_two:TextView?=null
    var btn_two:Button ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.two)
        initView()
    }

    private fun initView() {
        tv_two= findViewById(R.id.tv_two) as TextView?
        btn_two= findViewById(R.id.btn_two) as Button?
        btn_two!!.setOnClickListener {
            EventBus.getDefault().register(this@SecondActivity);//接收粘性事件
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = true)
    fun onDataSynEvent(s:String) {
        tv_two!!.setText(s)
    }

    //点击返回
    public fun back(v:View){
        EventBus.getDefault().post(MessageEvent("这是一个普通的事件"));
//        startActivity(Intent(this@SecondActivity,MainActivity::class.java))
        finish()
    }

}
