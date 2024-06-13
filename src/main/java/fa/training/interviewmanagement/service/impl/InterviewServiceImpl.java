package fa.training.interviewmanagement.service.impl;

import fa.training.interviewmanagement.repository.InterviewRepository;
import fa.training.interviewmanagement.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

}
