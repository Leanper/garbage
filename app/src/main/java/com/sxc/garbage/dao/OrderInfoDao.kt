package com.sxc.garbage.dao

import androidx.room.*
import com.sxc.garbage.entity.OrderInfo
import com.sxc.garbage.entity.UserInfo

@Dao
interface OrderInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg OrderInfo: OrderInfo?)

    @Delete
    fun delete(OrderInfo: OrderInfo?)

    @Update
    fun update(OrderInfo: OrderInfo?)


    @Query("SELECT * FROM `ORDERINFO `")
    fun getAll(): List<OrderInfo?>?
    @Query("SELECT * FROM `ORDERINFO ` where id=:id")
    fun getAllById(id:Int): OrderInfo?

    @Query("SELECT  * FROM `ORDERINFO `where state=:state")
    fun getState(state:Int): List<OrderInfo?>?



    @Query("SELECT * FROM `ORDERINFO ` WHERE name = :name")
    fun findByName(name: String?): List<OrderInfo?>?

}