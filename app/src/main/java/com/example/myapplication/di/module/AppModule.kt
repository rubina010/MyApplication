package com.example.myapplication.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.utils.APP_DATABASE
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Singleton

@Module
class AppModule {
    companion object {
        @JvmField
        val MIGRATION_1_2 = MigrationTo2()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE).addMigrations(MIGRATION_1_2).build()

    @Singleton
    @Provides
    fun provideSharedPreference(application: Application) = application.defaultSharedPreferences
}

class MigrationTo2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE tbl_songs ADD lst TEXT;")
    }

}