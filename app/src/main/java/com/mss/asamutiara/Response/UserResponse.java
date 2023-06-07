package com.mss.asamutiara.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mss.asamutiara.Table.User;

public class UserResponse {

    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
