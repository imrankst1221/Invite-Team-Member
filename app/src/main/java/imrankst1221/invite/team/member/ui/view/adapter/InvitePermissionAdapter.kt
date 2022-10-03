package imrankst1221.invite.team.member.ui.view.adapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.data.model.InvitePermission

class InvitePermissionAdapter(
    var invitePermissions: ArrayList<InvitePermission>) : BaseAdapter() {

    override fun getCount(): Int {
       return invitePermissions.size
    }

    override fun getItem(position: Int): InvitePermission {
        return invitePermissions[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView: View? = convertView
        if (convertView == null) {
            convertView = View.inflate(parent.context, R.layout.item_invite_permission, null)
        }

        if(convertView != null) {
            val textViewTitle: TextView = convertView.findViewById(R.id.textViewTitle)
            textViewTitle.text = invitePermissions[position].name

            if (!isEnabled(position)) {
                textViewTitle.setTextColor(Color.GRAY)
                convertView.isEnabled = false
                convertView.setOnClickListener {
                    //NO-OP: Just intercept click on disabled item
                }
            }else{
                textViewTitle.setTextColor(Color.BLUE)
            }
        }
        return convertView
    }

     override fun isEnabled(position: Int): Boolean {
        return getItem(position).isEnabled
    }
}