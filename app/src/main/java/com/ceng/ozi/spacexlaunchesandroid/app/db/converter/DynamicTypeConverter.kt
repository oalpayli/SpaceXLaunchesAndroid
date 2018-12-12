package com.ceng.ozi.spacexlaunchesandroid.app.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class DynamicTypeConverter{

    @TypeConverter
    fun fromStringList(list: List<String>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toStringList(value: String?) : List<String> {
        if (value == null)
            return Collections.emptyList<String>()

        return Gson().fromJson(value, object : TypeToken<List<String>>(){}.type)
    }

    /*@TypeConverter
    fun fromProduct(product: ProductModel?): String? {
        return if (product == null) null else Gson().toJson(product)
    }

    @TypeConverter
    fun toProduct(value: String?): ProductModel? {
        return if (value == null) null else Gson().fromJson(value, ProductModel::class.java)
    }*/

}