package com.example.employeeinfo.commonFunction

import java.util.*

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

class Common {

    fun camelCase(words : String?):String{
        var output = ""
        if(!words.isNullOrBlank())
        {
            val wordsList = words.split(" ").toMutableList()
            for (word in wordsList) {
                output += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " "
            }
        }
        return output.trim()
    }

    fun composeEmail(address:String?): Intent {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        }
        return intent
    }

}