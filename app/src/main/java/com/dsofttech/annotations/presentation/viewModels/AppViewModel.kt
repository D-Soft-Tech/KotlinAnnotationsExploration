package com.dsofttech.annotations.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsofttech.annotations.domain.interactors.RemoteDataSourceRepository
import com.dsofttech.annotations.domain.models.PostDomain
import com.dsofttech.annotations.domain.models.UserDomain
import com.dsofttech.annotations.presentation.viewStates.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val remoteDataSourceRepository: RemoteDataSourceRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _users: MutableLiveData<ViewState<List<UserDomain>>> =
        MutableLiveData(ViewState.initialDefault(null))
    val users: LiveData<ViewState<List<UserDomain>>> get() = _users

    private val _posts: MutableLiveData<ViewState<List<PostDomain>>> =
        MutableLiveData(ViewState.initialDefault(null))
    val posts: LiveData<ViewState<List<PostDomain>>> get() = _posts

    fun loadDataFromRemote() {
        viewModelScope.launch(ioDispatcher) {
            _users.postValue(remoteDataSourceRepository.getUsers())
            _posts.postValue(remoteDataSourceRepository.getPosts())
        }
    }
}
