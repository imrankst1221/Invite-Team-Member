package imrankst1221.invite.team.member.data.api

import imrankst1221.invite.team.member.utilities.ApiException
import retrofit2.Response

abstract class SafeApiRequest{
    suspend fun<T : Any> apiRequest(call : suspend () -> Response<T>) : T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            message.append(error)
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}