package com.shay.ibiandroidtask

import android.app.Application
import com.shay.ibiandroidtask.data.Graph

class IBIApp: Application() {
    override fun onCreate(){
        super.onCreate()
        Graph.provide(this)
    }
}