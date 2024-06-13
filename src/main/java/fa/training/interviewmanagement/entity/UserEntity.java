package fa.training.interviewmanagement.entity;

import fa.training.interviewmanagement.model.StatusUserEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<Candidate> candidateList;

    @ManyToMany
    @JoinTable(
            name = "user_job",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "jobId"))
    Set<Job> jobSet;

    @ManyToMany
    @JoinTable(
            name = "user_interview",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "interviewId"))
    Set<Interview> interviewSet;
}
