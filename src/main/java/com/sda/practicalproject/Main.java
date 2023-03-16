package com.sda.practicalproject;

import com.sda.practicalproject.utils.SessionManager;

public class Main {
    public static void main(String[] args) {
        SessionManager.getSesstionFactory();

        SessionManager.shutDown();

    }
}