package imrankst1221.invite.team.member.data.model
import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("members")
    val members: Members? = null,
    @SerializedName("plan")
    val plan: Plan? = null
)

data class Members(
    @SerializedName("administrators")
    val administrators: Int? = null,
    @SerializedName("editors")
    val editors: Int? = null,
    @SerializedName("managers")
    val managers: Int? = null,
    @SerializedName("members")
    val members: Int? = null,
    @SerializedName("supporters")
    val supporters: Int? = null,
    @SerializedName("total")
    val total: Int? = null
)

data class Plan(
    @SerializedName("memberLimit")
    val memberLimit: Int? = null,
    @SerializedName("supporterLimit")
    val supporterLimit: Int? = null
)