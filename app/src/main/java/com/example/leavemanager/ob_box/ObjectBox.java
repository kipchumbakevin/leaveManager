package com.example.leavemanager.ob_box;

import android.content.Context;

import com.example.leavemanager.models.MyObjectBox;

import io.objectbox.BoxStore;


public class ObjectBox {
    private static BoxStore boxStore;
    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
    }
    public static BoxStore get() { return boxStore; }
}