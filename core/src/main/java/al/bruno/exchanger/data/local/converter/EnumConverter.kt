package al.bruno.exchanger.data.local.converter

import al.bruno.exchanger.data.local.model.RuleType
import al.bruno.exchanger.data.local.model.TransactionType
import androidx.room.TypeConverter

class EnumConverter {
    @TypeConverter
    fun fromType(value: TransactionType): String {
        return value.name
    }

    @TypeConverter
    fun toType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }

    @TypeConverter
    fun fromActionType(value: RuleType): String {
        return value.name
    }

    @TypeConverter
    fun toActionType(value: String): RuleType {
        return RuleType.valueOf(value)
    }
}