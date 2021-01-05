package com.dstarlab.dstarcodegeneration.dynamicinjector

class RoomDaoGenerationObjectBuilder(className: String,
                                     packageName: String,
                                     suffix: String,
                                     tableName: String) {

    private val contentTemplate = """
        package $packageName
        
        import androidx.lifecycle.LiveData
        import androidx.room.*
        import com.anzid.utils.room.BaseDao

        interface $className<T> {

            @Query(value = "SELECT * FROM $tableName")
            fun getAll${suffix}s(): LiveData<List<T>>

            @Query(value = "SELECT * FROM $tableName")
            fun getList${suffix}(): List<T>

            @Query(value = "DELETE FROM $tableName")
            fun cleanTable()
            
        }
    """.trimIndent()

    fun getContent() = contentTemplate
}