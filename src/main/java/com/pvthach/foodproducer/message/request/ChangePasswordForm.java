package com.pvthach.foodproducer.message.request;

/**
 * Created by THACH-PC
 */

public class ChangePasswordForm {

    private String oldPassword;

    private String newPassword;

    public ChangePasswordForm(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
    public ChangePasswordForm() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}