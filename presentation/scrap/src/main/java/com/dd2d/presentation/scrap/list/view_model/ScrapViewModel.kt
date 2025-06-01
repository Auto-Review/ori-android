package com.dd2d.presentation.scrap.list.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.domain.code_post.model.scrap.CodePostScrapListOption
import com.dd2d.domain.code_post.repository.CodePostScrapRepository
import com.dd2d.domain.til.model.TILScrapListOption
import com.dd2d.domain.til.repository.TILScrapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ScrapViewModel @Inject constructor(
    private val codePostScrapRepository: CodePostScrapRepository,
    private val tilScrapRepository: TILScrapRepository
) : ViewModel() {
    val codePostScrapListController = LazyListController(
        option = CodePostScrapListOption(),
        scope = viewModelScope,
        getList = codePostScrapRepository::getMyCodePostScrapList,
    )

    val tilScrapListController = LazyListController(
        option = TILScrapListOption(),
        scope = viewModelScope,
        getList = tilScrapRepository::getMyScrapList,
    )
}