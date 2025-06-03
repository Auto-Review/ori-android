package com.dd2d.presentation.my.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.onStateSuccess
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.domain.auth_user.user.repository.UserRepository
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.domain.til.model.TILListOptions
import com.dd2d.domain.til.repository.TILRepository
import com.dd2d.presentation.my.model.UserUpdateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
internal class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val codePostRepository: CodePostRepository,
    private val tilRepository: TILRepository,
): ViewModel() {
    var me by mutableStateOf<User?>(null); private set
    private fun refreshMe() {
        userRepository.me()
            .onStateSuccess { 
                me = this
                userUpdateState.initUser(this)
            }
            .launchIn(viewModelScope)
    }

    val userUpdateState = UserUpdateState()
    fun updateUser() {
        userUpdateState.taskFlow(userRepository::updateMe)
            .onStateSuccess { refreshMe() }
            .launchIn(viewModelScope)
    }

    val codePostListController = LazyListController(
        option = CodePostListOptions(),
        scope = viewModelScope,
        getList = codePostRepository::getMyCodePostList,
    )

    val tilListController = LazyListController(
        option = TILListOptions(),
        scope = viewModelScope,
        getList = tilRepository::getMyTILList,
    )

    init { refreshMe() }
}
