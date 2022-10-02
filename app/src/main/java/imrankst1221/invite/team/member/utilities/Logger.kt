package imrankst1221.invite.team.member.utilities

import android.util.Log
import imrankst1221.invite.team.member.BuildConfig

object Logger {
    private val SHOULD_LOG: Boolean = BuildConfig.DEBUG

    fun i(tag: String, string: String) {
        if (SHOULD_LOG) Log.i(tag, string)
    }

    fun e(tag: String, string: String) {
        if (SHOULD_LOG) Log.e(tag, string)
    }

    fun d(tag: String, string: String) {
        if (SHOULD_LOG) Log.d(tag, string)
    }

    fun v(tag: String, string: String) {
        if (SHOULD_LOG) Log.v(tag, string)
    }

    fun w(tag: String, string: String) {
        if (SHOULD_LOG) Log.w(tag, string)
    }
}