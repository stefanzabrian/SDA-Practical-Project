package com.sda.practicalproject.controller.menu;

public enum MenuItem {
    ADD_VET(1, "Add Vet"),
    UPDATE_VET(2, "Update Vet"),
    DELETE_VET(3, "Delete Vet"),
    VIEW_VET_LIST(4, "View Vet List"),
    VIEW_VET_BY_ID(5, "View Vet by id"),
    ADD_PET(6, "Add Pet"),
    VIEW_PET_LIST(7, "View Pet List"),
    VIEW_PET_BY_ID(8,"View Pet by id"),
    DELETE_PET_BY_ID(9,"Delete Pet by id"),
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
