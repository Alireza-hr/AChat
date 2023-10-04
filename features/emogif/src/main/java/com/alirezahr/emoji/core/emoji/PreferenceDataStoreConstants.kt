package com.alirezahr.emoji.core.emoji

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceDataStoreConstants {
    val KEY_PREF_PAGE = intPreferencesKey("recent_page")
    val KEY_PREF_RECENT = stringPreferencesKey("recent_emojis")
}