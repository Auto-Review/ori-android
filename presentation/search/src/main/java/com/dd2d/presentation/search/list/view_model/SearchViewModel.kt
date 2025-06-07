package com.dd2d.presentation.search.list.view_model

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.domain.til.model.TILListOptions
import com.dd2d.domain.til.repository.TILRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
internal class SearchViewModel @Inject constructor(
  private val codePostRepository: CodePostRepository,
  private val tilRepository: TILRepository,
) : ViewModel() {
  val keyword = TextFieldState()

  val codePostListController = LazyListController(
    lazyInit = true,
    option = CodePostListOptions(),
    scope = viewModelScope,
    getList = codePostRepository::getCodePostList,
  )

  val tilPostListController = LazyListController(
    lazyInit = true,
    option = TILListOptions(),
    scope = viewModelScope,
    getList = tilRepository::getTILList,
  )

  init {
    viewModelScope.launch {
      snapshotFlow { keyword.text }
        .debounce { keyword ->
          if(keyword.isEmpty()) 0L
          else 200L
        }
        .collectLatest { keyword ->
          codePostListController.updateOption { prev ->
            prev.copy(search = keyword.toString())
          }
          tilPostListController.updateOption { prev ->
            prev.copy(search = keyword.toString())
          }
        }
    }
  }
}