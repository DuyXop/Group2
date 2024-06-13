package fa.training.interviewmanagement.controller;

import fa.training.interviewmanagement.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InterviewController {
    @Autowired
    private InterviewService interviewService;
}
