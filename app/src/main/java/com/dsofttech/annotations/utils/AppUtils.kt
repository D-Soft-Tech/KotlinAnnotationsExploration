package com.dsofttech.annotations.utils

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes

object AppUtils {

    fun Activity.showToast(@StringRes stringMessageId: Int) {
        Toast.makeText(this, getString(stringMessageId), Toast.LENGTH_SHORT)
            .show()
    }
}
