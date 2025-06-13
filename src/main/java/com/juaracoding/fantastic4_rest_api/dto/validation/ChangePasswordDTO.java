package com.juaracoding.fantastic4_rest_api.dto.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ChangePasswordDTO {
//    @NotNull(message = "Username Tidak Boleh Null")
//    @NotBlank(message = "Username Tidak Boleh Blank")
//    @NotEmpty(message = "Username Tidak Boleh Kosong")
//    private String username;

    @NotNull(message = "Current Password Tidak Boleh Null")
    @NotBlank(message = "Current Password Tidak Boleh Blank")
    @NotEmpty(message = "Current Password Tidak Boleh Kosong")
    private String currentPassword;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w].{8,15}$",
            message = "Format Password Tidak Valid")
    private String newPassword;

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
