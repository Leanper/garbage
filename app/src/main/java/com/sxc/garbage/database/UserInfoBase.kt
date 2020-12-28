package com.sxc.garbage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sxc.garbage.dao.UserInfoDao
import com.sxc.garbage.entity.UserInfo

@Database(entities = [UserInfo::class], version = 1)
abstract class UserInfoDataBase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao?
}