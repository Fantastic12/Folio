package com.dstarlab.dstarannotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class RoomDaoGeneration(val tableName: String)