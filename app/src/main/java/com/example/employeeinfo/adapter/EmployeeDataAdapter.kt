package com.example.employeeinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeinfo.R
import com.example.employeeinfo.activity.MainActivity
import com.example.employeeinfo.api.Result
import com.example.employeeinfo.commonFunction.Common
import java.util.*
import android.content.Intent

import androidx.core.content.ContextCompat.startActivity




class EmployeeDataAdapter(private var dataList: List<Result?>, private val action:MainActivity): RecyclerView.Adapter <ViewHolder> ()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.employee_item, parent, false))
    }

    override fun getItemCount(): Int
    {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val employeeInfo = dataList[position]
        if(employeeInfo!=null)
        {
            holder.initialize(employeeInfo, action)
        }
    }
}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    var employeeName: TextView = itemView.findViewById(R.id.employee_name)
    var employeeMail: TextView = itemView.findViewById(R.id.employee_mail)

    fun initialize(employeeInfo: Result, action: MainActivity) {
        val email = employeeInfo.eMail?.lowercase()
        employeeName.text = Common().camelCase(employeeInfo.employeeName)
        employeeMail.text = email
        employeeMail.setOnClickListener {
            startActivity(action,Intent.createChooser(Common().composeEmail(email),"Send Email"),null)
        }
        itemView.setOnClickListener {
            action.onItemClick(employeeInfo)
        }
    }
}