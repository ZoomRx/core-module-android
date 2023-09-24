package com.zoomrx.coremodule

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

class AppContainerInitializer : Initializer<AppContainer> {
    override fun create(context: Context): AppContainer {
        AppContainer.initialize(context as Application)
        return AppContainer.getInstance()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}