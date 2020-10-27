package com.example.backtracecc

import backtraceio.library.BacktraceClient
import kotlin.Exception

class NativeBackTrace {
    companion object {

        lateinit var btClient: BacktraceClient
        lateinit var activity: MainActivity

        fun init(activity: MainActivity)
        {
            System.loadLibrary("backtrace")
            this.activity = activity
        }

        @JvmStatic
        fun setBTClient(client: BacktraceClient){
            btClient = client
        }

        @JvmStatic
        fun callBackTrace(e: Exception){
            activity.backtraceClient.send(e)
        }
        external fun CauseCrash()
    }
}
