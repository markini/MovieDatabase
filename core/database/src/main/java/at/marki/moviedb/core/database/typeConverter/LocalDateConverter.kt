package at.marki.moviedb.core.database.typeConverter

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun dateStringToLocalDate(value: Int?): LocalDate? =
        value?.let(LocalDate::fromEpochDays)

    @TypeConverter
    fun localDateToDateString(localDate: LocalDate?): Int? =
        localDate?.toEpochDays()
}
