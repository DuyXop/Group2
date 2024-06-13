package fa.training.interviewmanagement.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Integer userId;
    private String username;
    private String role;
    private LocalDate dob;
    private String phone;
    private String email;
    private String address;
    private String gender;
    private String department;
    private String status;
    private String note;
}
