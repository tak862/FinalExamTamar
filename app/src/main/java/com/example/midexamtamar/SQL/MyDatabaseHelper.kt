import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Movie(
    var id: Int = 0,
    val name: String,
    val description: String,
    val releaseDate: Int,
    val time: Double,
    val country: String,
    val imageUrl: String
)


class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "movies.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "movies"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_RELEASE_DATE = "releaseDate"
        private const val COLUMN_TIME = "time"
        private const val COLUMN_COUNTRY = "country"
        private const val COLUMN_IMAGE_URL = "imageUrl"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_DESCRIPTION TEXT NOT NULL,
                $COLUMN_RELEASE_DATE INTEGER,
                $COLUMN_TIME REAL,
                $COLUMN_COUNTRY TEXT,
                $COLUMN_IMAGE_URL TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addMovie(movie: Movie): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, movie.name)
            put(COLUMN_DESCRIPTION, movie.description)
            put(COLUMN_RELEASE_DATE, movie.releaseDate)
            put(COLUMN_TIME, movie.time)
            put(COLUMN_COUNTRY, movie.country)
            put(COLUMN_IMAGE_URL, movie.imageUrl)
        }
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }

    fun getAllMovies(): List<Movie> {
        val movies = mutableListOf<Movie>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, "$COLUMN_ID DESC")
        if (cursor.moveToFirst()) {
            do {
                movies.add(
                    Movie(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                        releaseDate = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RELEASE_DATE)),
                        time = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TIME)),
                        country = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COUNTRY)),
                        imageUrl = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_URL))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return movies
    }
}