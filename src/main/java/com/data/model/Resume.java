package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String education;
    private String experience;
    private String skills;
}
