package com.example.hobbyapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
//import com.example.hobbyapp.model.NewsDatabase
import com.example.hobbyapp.model.UserDatabase


val DB_NAME = "project_uas_anmp"

// Untuk Table User
fun buildDB(context: Context): UserDatabase {
    val db = Room.databaseBuilder(
        context,
        UserDatabase::class.java,
        DB_NAME
    )
        .addMigrations(MIGRATION_1_2)
        .addMigrations(MIGRATION_2_3)
        .build()
    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE user ADD COLUMN email VARCHAR(45) DEFAULT '' NOT NULL"
        )
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add the necessary SQL to migrate from version 2 to 3
        database.execSQL("CREATE TABLE IF NOT EXISTS `news` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `judul` TEXT NOT NULL, `url` TEXT NOT NULL, `deskripsi` TEXT NOT NULL, `pengarang` TEXT NOT NULL, `detail` TEXT NOT NULL)")
    }
}

//val MIGRATION_3_4 = object : Migration(3, 4) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        // Add the necessary SQL to migrate from version 2 to 3
//        database.execSQL("INSERT INTO news (judul, url, deskripsi, pengarang, detail)\n" +
//                "VALUES('New Gundam Seed Movie!', 'https://en.gundam.info/about-gundam/series-pages/seedfreedom/img/common/visual/main.jpg?v2', 'Mobile Suit Gundam SEED Freedom is a 2024 Japanese animated military science fiction action film produced by Bandai Namco Filmworks and its animation studio Sunrise and directed by Mitsuo Fukuda.', 'andrebot', 'Based on tentatively leaked information, the Rising Freedom and Immortal Justice are Battery-Powered upgraded variants of the original Freedom and Justice to comply with Non-Nuclear Regulations, with the Strike Freedom and Infinite Justice mothballed and upgraded .');")
//        database.execSQL("INSERT INTO News (judul, url, deskripsi, pengarang, detail)\n" +
//                "VALUES('Gundam Iron Blooded Orphans Urdr Hunt Anime is Here!', 'https://i0.wp.com/anitrendz.net/news/wp-content/uploads/2024/01/mobilesuitgundamironbloodedorphansurdrhunt_animeteaservisual-1-e1704970621882.jpg?resize=696%2C393&ssl=1', 'Sunrise through its portal site Gundam.info has announced that Mobile Suit Gundam: Iron-Blooded Orphans: Urðr-Hunt, an anime spinoff of the anime series Mobile Suit Gundam: Iron-Blooded Orphans, will be adapted into a standalone anime.', 'andrebot', 'This spinoff, titled Kido Senshi Gundam: Tekketsu no Orphans: Urðr-Hunt, was announced by Sunrise and Bandai Namco Entertainment in 2019 and debuted in the Kido Senshi Gundam: Tekketsu no Orphans G app last year. Kido Senshi Gundam: Tekketsu no Orphans G is an app that combines anime and game elements, and brings back elements of the original anime as well as Kido Senshi Gundam: Tekketsu no Orphans: Urðr-Hunt.');")
//    }
//}

// Untuk Table News
//fun buildDb(context: Context):NewsDatabase {
//    val db = Room.databaseBuilder(context,
//        NewsDatabase::class.java, DB_NAME)
//        .addMigrations(MIGRATION_NEWS_1_2)
//        .addMigrations(MIGRATION_NEWS_2_3)
//        .build()
//
//    return db
//}
//
//val MIGRATION_NEWS_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "ALTER TABLE news ADD COLUMN kategori STRING DEFAULT '' not null"
//        )
//    }
//}
//
//val MIGRATION_NEWS_2_3 = object : Migration(2, 3) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "ALTER TABLE news DROP COLUMN kategori"
//        )
//    }
//}
