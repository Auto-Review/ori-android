package com.dd2d.presentation.code_post.list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.code_post.repository.CodePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CodePostListViewModel @Inject constructor(
    private val codePostRepository: CodePostRepository
): ViewModel() {
    val listController = LazyListController(
        option = CodePostListOptions(take = 10),
        scope = viewModelScope,
        getList = codePostRepository::getCodePostList,
    )
}




