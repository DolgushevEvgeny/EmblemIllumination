package com.example.emblemillumination

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BluetoothConnectViewModel(
    private val bluetoothManager: BluetoothManager
) : ViewModel() {

    val bluetoothDevices: LiveData<List<BluetoothDevice>>
        get() = bluetoothDevicesData

    private val bluetoothDevicesData = MutableLiveData<List<BluetoothDevice>>()

    fun isBluetoothEnabled(): Boolean = bluetoothManager.inBluetoothEnabled()

    fun findBluetoothDevices() {
        bluetoothDevicesData.postValue(bluetoothManager.findBluetoothDevices())
    }
}