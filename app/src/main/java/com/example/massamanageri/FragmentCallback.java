package com.example.massamanageri;

// This class is used as an interface between fragments and activities
public interface FragmentCallback {
    void changeFragmentToCreate();
    void loadMainMenu(String u);
    void loadLoginFragment();
}
