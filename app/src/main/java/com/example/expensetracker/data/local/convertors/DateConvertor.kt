package com.example.expensetracker.data.local.convertors

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date


@TypeConverters
class DateConvertor {

    @TypeConverter
    fun toDate(dateLong: Long?):Date?{

        return  dateLong?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun fromDate(date: Date):Long{
        return date.time
    }
}