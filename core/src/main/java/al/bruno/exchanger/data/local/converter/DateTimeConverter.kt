package al.bruno.exchanger.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor


class DateTimeConverter {
    @TypeConverter
    fun toLocalDate(value: String?): LocalDate =
        DateTimeFormatter.ISO_LOCAL_DATE.parse(
            value
        ) { temporal: TemporalAccessor? ->
            LocalDate.from(
                temporal
            )
        }

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String =
        date.format(DateTimeFormatter.ISO_LOCAL_DATE)

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime =
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(
            value
        ) { temporal: TemporalAccessor? ->
            LocalDateTime.from(
                temporal
            )
        }

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime): String =
        date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}