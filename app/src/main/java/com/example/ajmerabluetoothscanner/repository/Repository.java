package com.example.ajmerabluetoothscanner.repository;


import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static final Repository INSTANCE = new Repository();

    private final List<String> mData = new ArrayList<>();

    public static Repository getInstance(){
        return INSTANCE;
    }


    public List<String> getData(){
        return mData;
    }

    public void addDataSource(String data){
        mData.add(data);
    }

    public void removeDataSource(){
        mData.clear();
    }
}
