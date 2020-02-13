package com.app.pinchtozoom.ui.mainscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.pinchtozoom.R
import com.app.pinchtozoom.api.responses.Users
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_users.view.*

class UsersAdapter(
    private val context: Context,
    private var usersList: MutableList<Users>,
    private var iOnClickUser: IOnClickUser
) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_users,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textViewAdpUserName?.text = usersList.get(position).login
        holder.textViewAdpUserType?.text = usersList.get(position).type
        Glide
            .with(context)
            .load(usersList.get(position).avatarUrl)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imageviewAdpUserImage);

        holder.linearLayoutCandidate.setOnClickListener {
            iOnClickUser?.onClickItem(usersList.get(position), position)
        }
    }

    fun setNewUsersList(candidates: List<Users>) {
        this.usersList.clear()
        usersList.addAll(candidates)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val linearLayoutCandidate = view.relativelayout_adp_user_parent
        val imageviewAdpUserImage = view.imageview_adp_user_image
        val textViewAdpUserName = view.textView_adp_user_name
        val textViewAdpUserType = view.textView_adp_user_type


    }
}