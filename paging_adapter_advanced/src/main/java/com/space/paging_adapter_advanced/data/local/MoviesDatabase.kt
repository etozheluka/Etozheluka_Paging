package com.space.paging_adapter_advanced.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.paging_adapter_advanced.data.models.Movie
import com.space.paging_adapter_advanced.data.models.RemoteKeys

@Database(
    entities = [Movie::class, RemoteKeys::class],
    version = 1,
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}