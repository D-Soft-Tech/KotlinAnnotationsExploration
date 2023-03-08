package com.dsofttech.annotations.presentation.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dsofttech.annotations.R
import com.dsofttech.annotations.presentation.viewModels.AppViewModel
import com.dsofttech.annotations.presentation.viewStates.Status
import com.dsofttech.annotations.utils.AppUtils.showToast
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val appViewModel: AppViewModel by viewModels()

    @Inject
    lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appViewModel.loadDataFromRemote()
    }

    override fun onResume() {
        super.onResume()
        appViewModel.posts.observe(this) {
            if (it.status == Status.SUCCESS) {
                it.content?.let { posts ->
                    showToast(R.string.posts_fetched_successfully)
                    Timber.d("POSTS====>%s", gson.toJson(posts))
                } ?: run {
                    Timber.d(
                        "POSTS====>%s",
                        "Request was successful but null was received as response",
                    )
                }
            }
        }

        appViewModel.users.observe(this) {
            if (it.status == Status.SUCCESS) {
                it.content?.let { users ->
                    showToast(R.string.users_fetched_successfully)
                    Timber.d("USERS====>%s", gson.toJson(users))
                } ?: run {
                    Timber.d(
                        "USERS====>%s",
                        "Request was successful but null was received as response",
                    )
                }
            }
        }
    }
}
