package com.aemiralfath.decare.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import com.aemiralfath.decare.R

object AlertDialogHelper {
    fun createAlertDialogHelper(context: Context, navController: NavController): AlertDialog {
        val alertDialogBuilder = AlertDialog.Builder(context).apply {
            setTitle(context.getString(R.string.warning))
            setMessage(context.getString(R.string.back_message))
            setPositiveButton(context.getString(R.string.back)) { _, _ ->
                navController.navigate(R.id.informationFragment)
            }
        }

        return alertDialogBuilder.create()
    }
}