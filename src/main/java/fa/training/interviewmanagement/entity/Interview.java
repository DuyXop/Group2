package fa.training.interviewmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer interviewId;
    private String title;
    private LocalDateTime time;
    private String location;
    private String meetingId;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobId")
    private Job job;

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL)
    private List<Offer> offerList;
}
