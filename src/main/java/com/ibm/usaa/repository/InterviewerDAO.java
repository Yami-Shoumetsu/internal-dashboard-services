package com.ibm.usaa.repository;

import java.util.Map;

import com.ibm.usaa.repository.entity.InterviewerVO;

public interface InterviewerDAO {
	
	public Map<String, InterviewerVO> getAllInterviewers();
	public InterviewerVO getInterviewerById(String id);
	public boolean addInterviewer(InterviewerVO interviewerVO);
	public boolean updateInterviewer(InterviewerVO interviewerVO);
	
}
