package com.example.ajmerabluetoothscanner.viewmodel;


import androidx.lifecycle.ViewModel;

import com.example.ajmerabluetoothscanner.repository.Repository;

import java.util.List;


public class FirstFragmentViewModel extends ViewModel {

    public List<String> getData(){
        return Repository.getInstance().getData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Repository.getInstance().removeDataSource();
    }
}
