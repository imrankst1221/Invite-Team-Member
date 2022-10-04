package imrankst1221.invite.team.member.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Invites(
    @SerializedName("url")
    val url: String? = null,
    @Expose(deserialize = false)
    var isQrNavigation: Boolean
)

data class InvitesRequest(
    @SerializedName("role")
    val role: String? = null
)



