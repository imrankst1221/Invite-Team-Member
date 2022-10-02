package imrankst1221.invite.team.member.data.api

import android.content.Context
import android.text.TextUtils
import android.util.Log
import java.io.IOException
import imrankst1221.invite.team.member.BuildConfig
import imrankst1221.invite.team.member.utilities.UtilMethods
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

class MockTeamDataInterceptor: Interceptor {
    val TAG = MockTeamDataInterceptor::class.java.simpleName
    lateinit var context: Context

    constructor(context: Context) {
        this.context = context
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        val path: String = chain.request().url.toUri().path
        response = interceptRequestWhenDebug(chain, path)
        if (null == response) {
            response = chain.proceed(chain.request())
        }
        return response
    }

    private fun interceptRequestWhenDebug(chain: Interceptor.Chain, path: String): Response? {
        var response: Response? = null
        if (BuildConfig.DEBUG) {
            val request: Request = chain.request()
            if (path.equals("/teams", ignoreCase = true)) {
                response = getMockEventListResponse(request)
            }
        }
        return response
    }

    private fun getMockEventListResponse(request: Request): Response? {
        val response: Response
        val data: String = UtilMethods.loadJSONFromAsset(context, "team_mock_case_1.json") ?: ""
        response = getHttpSuccessResponse(request, data)
        return response
    }

    private fun getHttpSuccessResponse(request: Request, dataJson: String): Response {
        val response: Response
        response = if (TextUtils.isEmpty(dataJson)) {
            Response.Builder()
                .code(500)
                .protocol(Protocol.HTTP_1_0)
                .request(request) //protocol&request be set,otherwise will be exception.
                .build()
        } else {
            Response.Builder()
                .code(200)
                .message(dataJson)
                .request(request)
                .protocol(Protocol.HTTP_1_0)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create("application/json".toMediaTypeOrNull(), dataJson))
                .build()
        }
        return response
    }
}