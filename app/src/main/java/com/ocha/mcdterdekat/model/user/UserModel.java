package com.ocha.mcdterdekat.model.user;

import com.google.gson.annotations.SerializedName;
import com.ocha.mcdterdekat.model.BaseModel;

import org.jetbrains.annotations.NotNull;

public class UserModel extends BaseModel {

    // variabel id
    // dengan nama untuk serialisasi
    @SerializedName("id")
    public int Id;

    // variabel id
    // dengan nama untuk serialisasi
    @SerializedName("name")
    public String Name;

    // variabel username
    // dengan nama untuk serialisasi
    @SerializedName("username")
    public String Username;

    // variabel password
    // dengan nama untuk serialisasi
    @SerializedName("password")
    public String Password;

    public UserModel() {
        super();
    }

    public UserModel(int id, String name, String username, String password) {
        Id = id;
        Name = name;
        Username = username;
        Password = password;
    }

    @NotNull
    public UserModel clone(){
        return new UserModel(this.Id,this.Name,this.Username,this.Password);
    }
}
