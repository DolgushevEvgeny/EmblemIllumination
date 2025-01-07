package com.example.emblemillumination

import android.app.Activity.RESULT_OK
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.emblemillumination.databinding.FragmentBluetoothConnectBinding

class BluetoothConnectFragment : Fragment(R.layout.fragment_bluetooth_connect) {

    private val viewBinding by viewBinding(FragmentBluetoothConnectBinding::bind)

    private val viewModel: BluetoothConnectViewModel by lazy {
        val application = MyApplication.get(requireContext())
        getViewModel {
            BluetoothConnectViewModel(
                bluetoothManager = application.bluetoothManager
            )
        }
    }

    private lateinit var recyclerViewDevices: RecyclerView
    private lateinit var bluetoothForResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bluetoothForResultLauncher = requireActivity().registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                findBluetoothDevices()
            } else {
                // TODO: добавить уведомление что нужно включить bluetooth
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewDevices = viewBinding.recyclerViewDevices
        findBluetoothDevices()

        viewModel.bluetoothDevices.observe(viewLifecycleOwner) { bluetoothDevices ->
            Log.d("BluetoothConnectFragment", "$bluetoothDevices")
        }
    }

    private fun findBluetoothDevices() {
        if (viewModel.isBluetoothEnabled()) {
            viewModel.findBluetoothDevices()
        } else {
            bluetoothForResultLauncher.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
        }
    }
}