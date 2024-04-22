package com.example.expensetracker.data.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.domain.model.Transcation
import java.util.Date


@Entity("transcactions_table")
data class TranscationDto(
    @PrimaryKey
    @ColumnInfo(name = "timestamp")
    val date: Date,

    @ColumnInfo(name = "entry_date")
    val datOfEntry: String,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "account")
    val account: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "transcation_type")
    val transcationType:String,

    @ColumnInfo(name = "transcation_title")
    val title:String,
){

    fun toTranscation():Transcation = Transcation(date, datOfEntry, amount,
        category, account,transcationType, title)

}