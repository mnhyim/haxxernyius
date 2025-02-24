package com.mnhyim.haxxernyius.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.mnhyim.haxxernyius.data.network.HackerNewsApi
import com.mnhyim.haxxernyius.data.network.dto.StoryDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val hackerNewsApi: HackerNewsApi
) : ViewModel() {

    private var _currentPage = MutableStateFlow(1)
    val currentPage = _currentPage.asStateFlow()

    private var _postsId = MutableStateFlow<List<Long>>(emptyList())
    val postsId = _postsId.asStateFlow()

    private var _posts = MutableStateFlow<List<StoryDto>>(emptyList())
    val posts = _posts.asStateFlow()

    private var start = 0
    private var end = 9

    init {
        Logger.d(messageString = "HomeViewModel init.", tag = "${this::class.simpleName}")
        getAllNewestStories()
    }

    private fun getAllNewestStories() {
        viewModelScope.launch {
            _postsId.value = hackerNewsApi.getBestStories()
            getStories(page = 0)
        }
    }

    fun getStories(page: Int) {
        _currentPage.value = page

        start += 10 * page
        end += 10 * page

        Logger.d(messageString = "$start - $end", tag = "${this::class.simpleName}")

        viewModelScope.launch {
            val result = mutableListOf<StoryDto>()
            for (i in (start..end)) {
                result.add(hackerNewsApi.getStoryDetail(postsId.value[i]))
            }
            _posts.value = result

            if ((start + 10) < postsId.value.size - 11) {

            }
        }
    }
}