package com.alirezahr.emoji.core.db

import androidx.annotation.IntDef


object EmojiCategories {

    const val RECENT = 0

    const val PEOPLE = 1

    const val NATURE = 2

    const val FOOD = 3

    const val ACTIVITY = 4

    const val TRAVEL = 5

    const val OBJECTS = 6

    const val SYMBOLS = 7

    const val FLAGS = 8

    @IntDef(*[RECENT, PEOPLE, NATURE, FOOD, ACTIVITY, TRAVEL, OBJECTS, SYMBOLS, FLAGS])
    internal annotation class EmoticonsCategory
}