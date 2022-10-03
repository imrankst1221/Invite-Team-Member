package imrankst1221.invite.team.member.data.extensions

import imrankst1221.invite.team.member.data.model.Team

object TeamExtensions {
    fun Team.currentMember(): Int {
        return (this.members?.administrators ?: 0) +
                (this.members?.managers ?: 0) +
                (this.members?.editors ?: 0) +
                (this.members?.members ?: 0)
    }

    fun Team.isSupporterLimitZero(): Boolean {
        return (this.plan?.supporterLimit ?: 0) == 0
    }

    fun Team.isAvailableMemberSlots(): Boolean {
        return this.currentMember() < (this.plan?.memberLimit ?: 0)
    }

    fun Team.isAvailableSupporterSlots(): Boolean {
        return (!this.isSupporterLimitZero() && ((this.members?.supporters
            ?: 0) < (this.plan?.supporterLimit ?: 0)))
    }
}