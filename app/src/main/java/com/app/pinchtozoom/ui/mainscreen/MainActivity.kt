package com.app.pinchtozoom.ui.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pinchtozoom.R
import com.app.pinchtozoom.UsersNetworkDataSource
import com.app.pinchtozoom.api.responses.Users
import com.app.pinchtozoom.ui.detailsscreen.DetailsActivity
import com.kotlinrxjava.demoapp.CustomeViewModelFactory
import com.kotlinrxjava.demoapp.apis.ApiInterfaces
import com.kotlinrxjava.demoapp.apis.RetrofitClient
import com.kotlinrxjava.demoapp.utils.Constant
import com.kotlinrxjava.demoapp.utils.NetworkState
import com.kotlinrxjava.demoapp.utils.Utilities
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IOnClickUser {

    private val TAG = Utilities.getTAGName(javaClass.simpleName)
    private lateinit var apiInterfaces: ApiInterfaces
    private lateinit var activityListAdapter: UsersAdapter
    private lateinit var mutableListUsers: ArrayList<Users>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: CustomeViewModelFactory
    private lateinit var usersNetworkDataSource: UsersNetworkDataSource

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val networkState = MutableLiveData<NetworkState>()
    private val userDetails = MutableLiveData<List<Users>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiInterfaces = RetrofitClient.getClient()

        usersNetworkDataSource =
            UsersNetworkDataSource(apiInterfaces, compositeDisposable, userDetails, networkState)

        val mainActivityRepository: MainActivityRepository =
            MainActivityRepository(apiInterfaces, usersNetworkDataSource)
        viewModelFactory = CustomeViewModelFactory()
        viewModelFactory.setMainActivityRepo(mainActivityRepository)
        viewModelFactory.setMainActivityMutableLiveData(userDetails, networkState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        var usersList = mutableListOf<Users>()
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView_act_main_userlist.layoutManager = layoutManager
        activityListAdapter = UsersAdapter(
            this,
            usersList,
            this
        )
        recyclerView_act_main_userlist.adapter = activityListAdapter

        viewModel.getUserListLiveData().observe(this, object : Observer<List<Users>> {
            override fun onChanged(t: List<Users>?) {
                Log.d(TAG, "Observer :--- ${t}")
                mutableListUsers = ArrayList<Users>()
                mutableListUsers.addAll(t!!)
                activityListAdapter.setNewUsersList(mutableListUsers)
            }
        })
        viewModel.getNetworkStateUsers().observe(this, object : Observer<NetworkState> {
            override fun onChanged(t: NetworkState?) {

                Toast.makeText(this@MainActivity, "State : ${t?.message}", Toast.LENGTH_LONG).show()
            }
        })

//        viewModel.userLiveData.observe(this, object : Observer<List<Users>> {
//            override fun onChanged(t: List<Users>?) {
//                Log.d(TAG, "Observer :--- ${t}")
//                mutableListUsers = ArrayList<Users>()
//                mutableListUsers.addAll(t!!)
//                activityListAdapter.setNewUsersList(mutableListUsers)
//            }
//        })
//        viewModel.networkStateUsers.observe(this, object : Observer<NetworkState> {
//            override fun onChanged(t: NetworkState?) {
//
//                Toast.makeText(this@MainActivity, "State : ${t?.message}", Toast.LENGTH_LONG).show()
//            }
//        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == Constant.VALUE_REQUEST_CODE) {
            if (resultCode == Constant.VALUE_RESULT_CODE) {
                var positionUser: Int? = data?.extras?.getInt("UserPosition")
                viewModel.removeUserData(positionUser!!)

//                mutableListUsers.removeAt(positionUser!!)
//                activityListAdapter.setNewUsersList(mutableListUsers)

                Toast.makeText(this@MainActivity, "positionUser : $positionUser", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

    override fun onClickItem(user: Users, position: Int) {

        Toast.makeText(this, "Pos : $position", Toast.LENGTH_LONG).show()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("Users", user)
        intent.putExtra("PositionUser", position)
        startActivityForResult(intent, Constant.VALUE_REQUEST_CODE)

    }

}
