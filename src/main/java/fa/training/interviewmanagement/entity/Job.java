package fa.training.interviewmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;
    private String title;
    private LocalDate startWork;
    private LocalDate endWork;
    private String address;
    private String description;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Interview> interviewList;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Offer> offerList;
}
