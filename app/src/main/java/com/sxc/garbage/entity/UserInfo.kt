package com.sxc.garbage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "USERINFO ")
class UserInfo {
    // 主键，是否自增长
    @PrimaryKey(autoGenerate = true)
    var id = 0

    // 表中字段，表中字段名
    @ColumnInfo(name = "name")
    var name: String? = null

    // 表中字段，表中字段名
    @ColumnInfo()
    var lastname: String? = null

    // 表中字段，默认使用下面字段名age
    @ColumnInfo()
    var password:String?= null
    // 表中字段，默认使用下面字段名sex
    @ColumnInfo
    var sex = 0

    // 表中字段，默认使用下面字段名sex
    @ColumnInfo
    var phoneNum:String?=null



    var address:String?=null




    override fun toString(): String {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", password=" + password +
                '}'
    }

}