package al.bruno.exchanger.data.local.converter

import al.bruno.exchanger.data.local.model.Type
import androidx.room.TypeConverter

class TransactionConverter {
    @TypeConverter
    fun fromTransaction(value: Type): String {
        return value.name
    }

    @TypeConverter
    fun toTransaction(value: String): Type {
        return Type.valueOf(value)
    }
}