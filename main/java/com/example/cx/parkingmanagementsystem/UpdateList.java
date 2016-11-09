package com.example.cx.parkingmanagementsystem;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Cx on 2016/11/08.
 */
public class UpdateList implements Serializable{
    private String ItemKind;
    private int ID;
    private int NewStatus;

    UpdateList(String ItemKind, int ID, int NewStatus) {
        super();
        this.ItemKind = ItemKind;
        this.ID = ID;
        this.NewStatus = NewStatus;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setItemKind(String itemKind) {
        ItemKind = itemKind;
    }

    public void setNewStatus(int newStatus) {
        NewStatus = newStatus;
    }

    public int getID() {
        return ID;
    }

    public int getNewStatus() {
        return NewStatus;
    }

    public String getItemKind() {
        return ItemKind;
    }
}
