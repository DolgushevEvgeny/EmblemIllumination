package com.example.emblemillumination

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {

        fun get(context: Context): MyApplication {
            return context.applicationContext as MyApplication
        }
    }

    val bluetoothManager: BluetoothManager by lazy {
        BluetoothManagerImpl(
            getSystemService(Context.BLUETOOTH_SERVICE) as android.bluetooth.BluetoothManager
        )
    }
}