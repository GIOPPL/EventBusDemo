package com.gioppl.eventbusdemo



/**
 * Created by GIOPPL on 2017/8/11.
 */
public class MessageEvent(message:String) {
    var message: String? = null
    init {
        this.message=message
    }
}