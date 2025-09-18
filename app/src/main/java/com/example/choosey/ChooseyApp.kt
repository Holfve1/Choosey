package com.example.choosey
import android.app.Application
class ChooseyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppGraph.provide(this)
    }
}