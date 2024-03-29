package com.norbertotaveras.android_nova.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.norbertotaveras.android_nova.data.local.dao.NewsDao
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article

@Database(entities = [Article::class],version = 8)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}