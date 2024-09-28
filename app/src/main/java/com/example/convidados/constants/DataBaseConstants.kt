package com.example.convidados.constants

class DataBaseConstants private constructor() {

    object GUEST {
        const val TABLE_NAME = "Guest"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }

        object PRESENCE{
            const val PRESENT = 1
            const val ABSENT = 0
        }

        object BUNDLE {
            const val GUESTID = "guestId"
        }

    }

    object SHAREDPREFERENCE{
        const val PREFERENCE_FILE_KEY_FRAGMENT = "com.example.myapp.PREFERENCE_FILE_KEY_FRAGMENT"

        object KEY_PREFERENCE{
            const val KEY_FRAGMENT = "fragment"
        }

    }

    object FRAGMENT_ID{
        const val ALL_FRAGMENT = 1
        const val PRESENT_FRAGMENT = 2
        const val ABSENT_FRAGMENT = 3
    }



}