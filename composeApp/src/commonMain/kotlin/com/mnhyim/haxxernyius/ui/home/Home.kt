package com.mnhyim.haxxernyius.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mnhyim.haxxernyius.data.network.dto.StoryDto
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Home(
    modifier: Modifier = Modifier,
) {
    val homeViewModel = koinViewModel<HomeViewModel>()

    val postsId by homeViewModel.postsId.collectAsStateWithLifecycle()
    val posts by homeViewModel.posts.collectAsStateWithLifecycle()

    val currentPage by homeViewModel.currentPage.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {

            Text(
                text = "Home",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(0.dp),
                modifier = Modifier.padding(top = 8.dp).weight(1f)
            ) {
                if (posts.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                } else {
                    itemsIndexed(items = posts) { index, item ->
                        PostItem(
                            index = index,
                            page = currentPage,
                            item = item,
                            modifier = Modifier.fillMaxWidth().clickable {}.padding(16.dp)
                        )
                        if (index < posts.size - 1) {
                            HorizontalDivider(thickness = 0.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
            PageIndicator(
                currentPage = currentPage,
                maxPages = (postsId.size / 10),
                onNext = { homeViewModel.getStories((currentPage) + 1) },
                onPrev = { homeViewModel.getStories((currentPage) - 1) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun PostItem(
    index: Int,
    page: Int,
    item: StoryDto,
    modifier: Modifier = Modifier
) {
    /* TODO: Should be simplified by using Domain class with formatted date instead of DTO object */
    val localDateTime by mutableStateOf(
        Instant.fromEpochSeconds(item.time).toLocalDateTime(TimeZone.currentSystemDefault())
    )
    /* TODO: This is embarassing */
    val date by mutableStateOf(
        "${
            localDateTime.date.dayOfWeek.name.subSequence(
                0,
                3
            )
        }, ${localDateTime.date.dayOfMonth} ${localDateTime.date.month.name} ${localDateTime.date.year}"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Icon(
//                imageVector = Icons.Default.KeyboardArrowUp,
//                contentDescription = "",
//                modifier = Modifier.size(16.dp)
//            )
//            Text(
//                text = "(${item.score})", style = MaterialTheme.typography.labelSmall
//            )
//        }
        Column(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp)
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Row {
                Text(
                    text = "by ${item.by} | ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "${item.score} Upvotes | ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "${item.descendants} Replies",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun PageIndicator(
    currentPage: Int,
    maxPages: Int,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalDivider()
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(onClick = { onPrev() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
            )
        }
        Text(
            text = "Page ${currentPage + 1} of $maxPages",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        IconButton(
            onClick = { onNext() },
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = ""
            )
        }
    }
}