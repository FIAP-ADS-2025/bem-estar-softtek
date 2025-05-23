package br.com.fiap.bemestarsofttek.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fiap.bemestarsofttek.database.dao.MoodEntryDao
import br.com.fiap.bemestarsofttek.database.entity.MoodEntryEntity
import br.com.fiap.bemestarsofttek.database.util.Converters

@Database(entities = [MoodEntryEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moodEntryDao(): MoodEntryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bemestar_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
