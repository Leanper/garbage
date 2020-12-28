package com.sxc.garbage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "ORDERINFO ")

class OrderInfo {
    // 主键，是否自增长
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo()
    var orderNum: Long = 0

    // 表中字段，表中字段名
    @ColumnInfo(name = "name")
    var name: String? = null
    // 表中字段，表中字段名 state 1为当前订单  2位取消订单  3为已完成（历史订单）
    @ColumnInfo()
    var state: Int = 0


    // 表中字段，默认使用下面字段名age
    @ColumnInfo()
    var address:String?= null

    // 表中字段，默认使用下面字段名age
    @ColumnInfo()
    var phoneNum:String?= null

    // 表中字段，默认使用下面字段名age
    @ColumnInfo()
    var time:String?= null




    override fun toString(): String {
        return "OrderInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", time='" + time + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", address=" + address +
                '}'
    }
}