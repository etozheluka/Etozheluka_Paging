package com.space.etozheluka_paging.data.remote.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.etozheluka_paging.data.remote.service.ServiceApi
import com.space.etozheluka_paging.model.MoviesDTO
import kotlin.math.max

private const val STARTING_KEY = 1

/**
 * A [PagingSource] that loads articles for paging. The [Int] is the paging key or query that is used to fetch more
 * data, and the [MoviesDTO] specifies that the [PagingSource] fetches an [MoviesDTO] [List].
 */
class MoviesPagingSource(private val serviceApi: ServiceApi) :
    PagingSource<Int, MoviesDTO.Result>() {

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, MoviesDTO.Result>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDTO.Result> {
        // If params.key is null, it is the first load, so we start loading with STARTING_KEY
        val startKey = params.key ?: STARTING_KEY
        // We fetch as many articles as hinted to by params.loadSize
        val range = startKey.until(startKey + params.loadSize)
        val response = serviceApi.getResponse(page = startKey)

        return LoadResult.Page(
            data = response.body()?.results ?: emptyList(),
            prevKey = when (startKey) {
                STARTING_KEY -> null
                else -> when (val prevKey = ensureValidKey(key = range.first - params.loadSize)) {
                    // We're at the start, there's nothing more to load
                    STARTING_KEY -> null
                    else -> prevKey
                }
            },
            nextKey = startKey + 1
        )

    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

}