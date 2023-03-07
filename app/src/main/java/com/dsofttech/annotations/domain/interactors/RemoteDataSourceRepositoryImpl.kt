package com.dsofttech.annotations.domain.interactors

import com.dsofttech.annotations.data.remote.apis.JsonPlaceHolderApiService
import com.dsofttech.annotations.domain.models.PostDomain
import com.dsofttech.annotations.domain.models.UserDomain
import com.dsofttech.annotations.utils.ModelMapper.toPostDomain
import com.dsofttech.annotations.utils.ModelMapper.toUserDomain
import com.pepsa.pepsadispatch.shared.presentation.viewStates.ViewState
import com.pepsa.pepsadispatch.shared.utils.networkUtils.NetworkUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceRepositoryImpl @Inject constructor(
    private val remoteApiService: JsonPlaceHolderApiService,
    private val networkUtils: NetworkUtils,
) : RemoteDataSourceRepository {
    override suspend fun getPosts(): ViewState<List<PostDomain>> {
        val posts = remoteApiService.getPosts()
        networkUtils.getServerResponse(posts).let { viewState ->
            return viewState.content?.let { postEntity ->
                ViewState(
                    content = postEntity.map { it.toPostDomain() },
                    status = viewState.status,
                    message = viewState.message,
                )
            } ?: run {
                ViewState(content = null, status = viewState.status, message = viewState.message)
            }
        }
    }

    override suspend fun getUsers(): ViewState<List<UserDomain>> {
        val users = remoteApiService.getUsers()
        networkUtils.getServerResponse(users).let { viewState ->
            return viewState.content?.let { userEntities ->
                ViewState(content = userEntities.map { it.toUserDomain() }, status = viewState.status, message = viewState.message)
            } ?: run {
                ViewState(content = null, status = viewState.status, message = viewState.message)
            }
        }
    }
}
