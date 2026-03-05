package com.duett.auth.dto;

public record ChangePasswordRequest(
        String currentPassword,
        String newPassword,
        String confirmNewPassword
) {}