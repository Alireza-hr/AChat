package com.alirezahr.emoji.core.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.util.query
import com.alirezahr.emoji.core.emoji.Emoji
import com.alirezahr.emoji.core.emoji.EmojiProvider
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper


internal class EmojiDataBaseHelper(context: Context) :
    SQLiteAssetHelper(context, DB_NAME, null, DB_VERSION) {
    init {
        setForcedUpgrade()
    }

    fun getEmoticons(
        @EmoticonsCategories.EmoticonsCategory category: Int,
        emoticonProvider: EmojiProvider?
    ): ArrayList<Emoji> {

        val emoticons: ArrayList<Emoji> = ArrayList()
        readableDatabase.apply {
            query(
                EmoticonColumns.TABLE, arrayOf(EmoticonColumns.UNICODE, EmoticonColumns.CATEGORY),
                EmoticonColumns.CATEGORY + "=?", arrayOf(category.toString() + ""),
                null, null,
                EmoticonColumns.ID + " ASC"
            ).apply {
                while (moveToNext()) {
                    val unicode = getString(getColumnIndex(EmoticonColumns.UNICODE))

                    if (emoticonProvider == null || emoticonProvider.hasEmojiIcon(unicode)) emoticons.add(
                        Emoji(unicode)
                    )
                }
            }.close()
        }.close()
        return emoticons
    }

    fun searchEmoticons(
        searchQuery: String,
        emoticonProvider: EmojiProvider?
    ): ArrayList<Emoji> {
        val sqLiteDatabase: SQLiteDatabase = readableDatabase
        val cursor = sqLiteDatabase.query(
            EmoticonTagsColumns.TABLE,
            arrayOf(EmoticonTagsColumns.UNICODE),  //Unicode.
            EmoticonTagsColumns.TAG + " LIKE ?",
            arrayOf(searchQuery.trim { it <= ' ' } + "%"),  //Search for the tag
            null,
            null,
            null)
        val emoticons: ArrayList<Emoji> = ArrayList<Emoji>()
        while (cursor.moveToNext()) {
            val unicode = cursor.getString(cursor.getColumnIndex(EmoticonTagsColumns.UNICODE))

            //Check if there is icon available to display for custom emoticon page.
            if (emoticonProvider == null || emoticonProvider.hasEmojiIcon(unicode)) {
                val emoticon = Emoji(unicode)
                if (!emoticons.contains(emoticon)) //Prevent duplicates.
                    emoticons.add(Emoji(unicode))
            }
        }
        cursor.close()
        sqLiteDatabase.close()
        return emoticons
    }

    @Suppress("unused")
    private object EmoticonColumns {
        const val TABLE = "emoticon"

        const val ID = "_id"

        const val UNICODE = "unicode"

        const val CATEGORY = "category"

        @Deprecated("")
        private val NAME = "name"
    }

    @Suppress("unused")
    private object EmoticonVariantColumns {
        private const val TABLE = "emoticon_variant"

        private const val ID = "variant_id"

        private const val UNICODE = "variant_unicode"

        private const val CATEGORY = "variant_category"

        private const val ROOT_UNICODE = "variant_root_unicode"

        @Deprecated("")
        private val NAME = "variant_name"
    }

    @Suppress("unused")
    private object EmoticonTagsColumns {
        const val TABLE = "emoticon_tags"

        private const val ID = "tags_id"

        const val UNICODE = "tags_unicode"

        const val TAG = "tags_tags"
    }

    companion object {
        private const val DB_NAME = "emoticon.db"

        private const val DB_VERSION = 1
    }
}
