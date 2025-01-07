package com.example.emblemillumination

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionHelper {

    companion object {

        fun isPermissionGranted(context: Context, permission: String): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}