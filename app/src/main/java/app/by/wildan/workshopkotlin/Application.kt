package app.by.wildan.workshopkotlin

import android.app.Application
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.google.GoogleEmojiProvider

class Application  : Application(){
    override fun onCreate() {
        super.onCreate()

        EmojiManager.install(GoogleEmojiProvider())

    }
}