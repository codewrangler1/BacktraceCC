package com.example.backtracecc

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import backtraceio.library.BacktraceClient
import backtraceio.library.BacktraceCredentials
import backtraceio.library.models.json.BacktraceReport

class MainActivity : AppCompatActivity() {

    // replace with your endpoint url and token
    val TAG: String = this.javaClass.name;

    lateinit var backtraceCredentials: BacktraceCredentials
    lateinit var backtraceClient: BacktraceClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {
            backtraceCredentials = BacktraceCredentials(
                    "https://codewrangler.sp.backtrace.io:6098",
                    "c9455ab6afd549487cdab6f0095fa5f1ea660a3b22c42ab23501c7b0228e9757"
            )
            backtraceClient = BacktraceClient(applicationContext, backtraceCredentials)

            Thread.setDefaultUncaughtExceptionHandler(ReportHelper(this));

        } catch (e: Exception) {
            Log.d(TAG, e.localizedMessage as String)
        }

        // Load native code
        NativeBackTrace.init(this)
        NativeBackTrace.setBTClient(backtraceClient)

    }

    fun onClickCrashKotlin(view: android.view.View) {


        Thread(Runnable {
            val npString: String? = null

            // force null pointer exception
            npString!!.length
        }).start()
    }

    fun onClickCrashCpp(view: android.view.View) {
        NativeBackTrace.CauseCrash()
    }

    class ReportHelper(mactivity: MainActivity) : Thread.UncaughtExceptionHandler {
        var context: MainActivity
        init {
            context = mactivity
        }

        fun ReportHelper(context: Context) {
            this.context = context as MainActivity
        }

        override fun uncaughtException(t: Thread, e: Throwable) {
            Log.d(context.TAG, e.localizedMessage as String)
            // Send exception to backtrace
            context.backtraceClient.send( BacktraceReport(e as java.lang.Exception));
        }

    }
}