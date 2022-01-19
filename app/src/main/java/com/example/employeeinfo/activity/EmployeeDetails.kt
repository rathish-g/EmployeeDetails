package com.example.employeeinfo.activity

import android.Manifest.permission.CALL_PHONE
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.employeeinfo.R
import com.example.employeeinfo.api.Result
import com.example.employeeinfo.commonFunction.Common
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class EmployeeDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)
        setupToolbar()
        val new = intent
        val data = new.getSerializableExtra("EmployeeObject") as Result
        val employeeId = findViewById<TextView>(R.id.employee_id)
        val employeeName = findViewById<TextView>(R.id.employee_name)
        val employeeMail = findViewById<TextView>(R.id.employee_mail)
        val employeeAddress = findViewById<TextView>(R.id.employee_address)
        val employeePhone = findViewById<TextView>(R.id.employee_phone)
        val companyName = findViewById<TextView>(R.id.company_name)
        val companyWebsite = findViewById<TextView>(R.id.company_website)

        employeeId.text = data.id.toString()
        employeeName.text = Common().camelCase(data.employeeName.toString())
        employeeMail.text = data.eMail.toString().lowercase()
        employeeAddress.text = (data.address?.suite+", "+data.address?.street+", "+data.address?.city+"-"+data.address?.zipcode).toString()
        employeePhone.text = data.phoneNumber.toString()

        employeeMail.setOnClickListener {
            startActivity(Common().composeEmail(data.eMail.toString().lowercase()))
        }

        employeePhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.phoneNumber.toString()))
            if (ContextCompat.checkSelfPermission(this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(CALL_PHONE),1)
            }
        }

        companyName.text = data.company?.companyName.toString()
        companyWebsite.text = data.website.toString()


    }

    @SuppressLint("SetTextI18n")
    private fun setupToolbar(){
        val actionbar = supportActionBar
        val textview = TextView(this)
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textview.layoutParams = layoutParams
        textview.text = "Employee Detail"
        textview.typeface = Typeface.DEFAULT_BOLD
        textview.setTextColor(Color.WHITE)
        textview.textSize = 18f
        actionbar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionbar?.customView = textview
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}