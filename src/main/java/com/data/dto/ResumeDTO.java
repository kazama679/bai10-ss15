package com.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDTO {
    private int id;
    @NotBlank(message = "Tên không được để trống!")
    private String fullName;
    @NotBlank(message ="Email không được để trống!")
    @Email(message = "Email không đúng địng dạng!")
    private String email;
    @NotBlank(message = "Số điện thoại không được để trống!")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không đúng định dạng!")
    private String phoneNumber;
    private String education;
    private String experience;
    private String skills;
}