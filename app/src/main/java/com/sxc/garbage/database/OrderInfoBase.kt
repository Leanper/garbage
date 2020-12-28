package com.sxc.garbage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sxc.garbage.dao.OrderInfoDao
import com.sxc.garbage.entity.OrderInfo

@Database(entities = [OrderInfo::class], version = 1)
abstract class OrderInfoBase : RoomDatabase() {
        abstract fun orderInfoDao(): OrderInfoDao?
}