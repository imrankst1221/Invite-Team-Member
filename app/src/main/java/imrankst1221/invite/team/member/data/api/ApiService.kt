package imrankst1221.invite.team.member.data.api

import android.content.Context
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.os.SystemClock
import imrankst1221.invite.team.member.data.model.Invites
import imrankst1221.invite.team.member.data.model.InvitesRequest
import imrankst1221.invite.team.member.data.model.Team
import imrankst1221.invite.team.member.utilities.Constants
import okhttp3.Interceptor
import retrofit2.http.*
import java.io.IOException
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("teams")
    suspend fun getTeams(
        @Query("teamId") appId: String
    ): Response<Team>

    @POST("teams/{teamId}/invites")
    suspend fun getInvitesUrl(
        @Path("teamId") teamId: String,
        @Body invitesRequest: InvitesRequest
    ): Response<Invites>

    companion object {
        fun create(context: Context): ApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val dispatcher = Dispatcher().apply { maxRequests = 1 }
            val interceptor: Interceptor = Interceptor { chain ->
                SystemClock.sleep(1000)
                chain.proceed(chain.request())
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(MockTeamDataInterceptor(context)) //using okhttp3 interceptor fake response.
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .addNetworkInterceptor(interceptor)
                .dispatcher(dispatcher)
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}