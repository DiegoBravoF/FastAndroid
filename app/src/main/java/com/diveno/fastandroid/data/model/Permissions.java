package com.diveno.fastandroid.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diego on 15/06/2016.
 */
public class Permissions {

    /**
     * admin : false
     * push : false
     * pull : true
     */

    @SerializedName("admin")
    private boolean admin;
    @SerializedName("push")
    private boolean push;
    @SerializedName("pull")
    private boolean pull;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public boolean isPull() {
        return pull;
    }

    public void setPull(boolean pull) {
        this.pull = pull;
    }
}
