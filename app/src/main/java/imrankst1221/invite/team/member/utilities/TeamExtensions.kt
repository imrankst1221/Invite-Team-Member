package imrankst1221.invite.team.member.utilities

/**
 * @author imran.choudhury
 * 31/10/21
 *
 * Android Extensions helper
 */

import imrankst1221.invite.team.member.data.model.Team
import imrankst1221.invite.team.member.utilities.TeamExtensions.currentMember
import imrankst1221.invite.team.member.utilities.TeamExtensions.isSupporterLimitZero

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