package com.sda.practicalproject.controler.menu;

import java.util.Arrays;

public enum MenuItem {
    ADD_VET(1, "Add Vet"),
    UPDATE_VET(2, "Update Vet"),
    DELETE_VET(3, "Delete Vet"),
    VIEW_VET_LIST(4, "View Vet List"),
    EXIT(100, "Exit"),
    UNKNOWN(999, "Unknown option");


    private final int option;
    private final String displayName;

    MenuItem(int option, String displayName) {
        this.option = option;
        this.displayName = displayName;
    }

    public static void printMenuItems() {
        for (MenuItem value : values()) {
            if (value != UNKNOWN) {
                System.out.println(value.option + " -> " + value.displayName);
            }
        }
    }

    public static MenuItem searchByOption(int inputOption) {
        for (MenuItem value : values()) {
            if (value.option == inputOption) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
