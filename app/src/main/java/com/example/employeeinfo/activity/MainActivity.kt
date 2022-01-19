package com.example.employeeinfo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeinfo.R
import com.example.employeeinfo.adapter.EmployeeDataAdapter
import com.example.employeeinfo.api.ApiClient
import com.example.employeeinfo.api.Result
import com.example.employeeinfo.interfaces.OnItemClickListerner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), OnItemClickListerner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        getData()
    }

    private fun getData(){
       val call: Call<List<Result?>?>? = ApiClient().getClient.getEmployeesInfo()
        call?.enqueue(object : Callback<List<Result?>?> {
            override fun onResponse(call: Call<List<Result?>?>?, response: Response<List<Result?>?>?) {
                val list = response?.body()
                if(!list.isNullOrEmpty())
                {
                    val employeeAdapter = EmployeeDataAdapter(list, this@MainActivity)
                    val employeeRecyclerView = findViewById<RecyclerView>(R.id.employeeRecyclerView)
                    employeeRecyclerView.adapter = employeeAdapter
                    employeeRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                }
            }

            override fun onFailure(call: Call<List<Result?>?>?, t: Throwable?) {
                Log.d("Retrofit_Error",t?.message.toString())
                Toast.makeText(this@MainActivity, "No Network Connection", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onItemClick(item: Result) {
        val intent = Intent(this, EmployeeDetails::class.java)
        intent.putExtra("EmployeeObject",item)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun setupToolbar(){
        val actionbar = supportActionBar
        val textview = TextView(this)
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)
        textview.layoutParams = layoutParams
        textview.text = "Employee List"
        textview.typeface = Typeface.DEFAULT_BOLD
        textview.setTextColor(Color.WHITE)
        textview.textSize = 18f
        actionbar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionbar?.customView = textview
    }
}