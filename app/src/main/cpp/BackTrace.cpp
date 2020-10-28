//
// Created by Michael McEuin on 10/26/20.
//

//#include "BackTrace.h"
#include <jni.h>
#include <android/log.h>
#include <stdexcept>

extern "C" {


    //static void CauseCrash() {
    extern void Java_com_example_backtracecc_NativeBackTrace_00024Companion_CauseCrash(JNIEnv *env, jobject thiz) {

        // "one should be a crash at the C/C++ layer"
        // Not sure how to create a crash and who would handle it
        // How do I get the Android code to handle it? Is there a C/C++ interface for backtrace?
        // I tried a few things here, with no success

// Look for exception and call back to NativeBackTrace
        // Find the Java class
        // jclass jniClass = env->FindClass("com/example/backtracecc/NativeBackTrace");

        jclass jniClass = env->FindClass("java/lang/Throwable");
        env->ThrowNew(jniClass, "Crash from NDK");

        // Find the Java method
        //jmethodID getCallBacktraceMethod = env->GetStaticMethodID(jniClass, "callBackTrace", "(Ljava/lang/Exception;)V");


        // exc = env->ExceptionOccurred();
        // if(exc != nullptr)
        // {
        //      env->CallStaticVoidMethod(jniClass, getCallBacktraceMethod, exc);
        //      env->ExceptionClear();
        // }

    }
}

