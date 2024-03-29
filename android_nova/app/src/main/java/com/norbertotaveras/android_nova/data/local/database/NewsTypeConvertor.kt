package com.norbertotaveras.android_nova.data.local.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.norbertotaveras.android_nova.data.remote.dto.source.Source

@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(',').let { sourceArray ->
            Source(id = sourceArray[0], name = sourceArray[1])
        }
    }
}