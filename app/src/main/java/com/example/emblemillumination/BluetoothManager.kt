package com.example.emblemillumination

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager as AndroidBluetoothManager

interface BluetoothManager {

    fun inBluetoothEnabled(): Boolean
    fun findBluetoothDevices(): List<BluetoothDevice>
}

class BluetoothManagerImpl(
    private val bluetoothManager: AndroidBluetoothManager
) : BluetoothManager {

    private val bluetoothAdapter = bluetoothManager.adapter

    override fun inBluetoothEnabled(): Boolean = bluetoothAdapter.isEnabled

    @SuppressLint("MissingPermission")
    override fun findBluetoothDevices(): List<BluetoothDevice> {
        return if (inBluetoothEnabled()) {
            bluetoothAdapter.getBondedDevices().toList()
        } else {
            emptyList()
        }
    }
}