package com.saiyoggie.themoviedbapp.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun formatDate(inputDate: Int?, requiredFormat: SimpleDateFormat): String? {
    var formatDate: String? = ""
    try {
        formatDate = requiredFormat.format(inputDate)
    } catch (e: ParseException) {
        e.printStackTrace()
        return ""
    }
    return formatDate
}