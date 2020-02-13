package com.app.pinchtozoom.ui.detailsscreen

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.app.pinchtozoom.R
import com.app.pinchtozoom.api.responses.Users
import com.bumptech.glide.Glide
import com.kotlinrxjava.demoapp.utils.Utilities
import kotlinx.android.synthetic.main.activity_details.*
import android.widget.Toast
import com.kotlinrxjava.demoapp.utils.Constant
import com.kotlinrxjava.demoapp.utils.Constant.VALUE_RESULT_CODE


class DetailsActivity : AppCompatActivity() {

    private var positionUser: Int = -1
    private val TAG: String = Utilities.getTAGName(javaClass.simpleName)
    private lateinit var users: Users
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var bundle = intent.extras
        if (bundle is Bundle) {
            users = bundle.getParcelable<Users>("Users") as Users
            positionUser = bundle.getInt("PositionUser")

            Glide
                .with(this)
                .load(users?.avatarUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.item_details_delete) {
            Toast.makeText(this@DetailsActivity, "Action clicked", Toast.LENGTH_LONG).show()
            val intent = Intent()
            intent.putExtra("UserPosition", positionUser)
            setResult( Constant.VALUE_RESULT_CODE, intent)
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


}