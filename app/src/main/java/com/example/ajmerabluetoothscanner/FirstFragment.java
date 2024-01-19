package com.example.ajmerabluetoothscanner;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ajmerabluetoothscanner.adapter.BluetoothRecyclerViewAdapter;
import com.example.ajmerabluetoothscanner.databinding.FragmentFirstBinding;
import com.example.ajmerabluetoothscanner.receiver.BluetoothReceiver;
import com.example.ajmerabluetoothscanner.viewmodel.FirstFragmentViewModel;

public class FirstFragment extends Fragment {

    private FirstFragmentViewModel viewModel;

    private FragmentFirstBinding binding;

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private BluetoothReceiver bluetoothReceiver;

    RecyclerView.Adapter adapter;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        viewModel = new ViewModelProvider(this).get(FirstFragmentViewModel.class);

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        bluetoothReceiver = new BluetoothReceiver();
        return binding.getRoot();

    }

    @SuppressLint("MissingPermission")
    void scanBluetoothDevice(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        getActivity().registerReceiver(bluetoothReceiver,filter);
        bluetoothAdapter.startDiscovery();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scanBluetoothDevice();
        binding.list.setAdapter(adapter);

        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("BlueResultFragment", viewModel.getData().toString());

                adapter = new BluetoothRecyclerViewAdapter(viewModel.getData());
                binding.list.setAdapter(adapter);

                binding.swiperefresh.setRefreshing(false);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        getActivity().unregisterReceiver(bluetoothReceiver);
    }

}