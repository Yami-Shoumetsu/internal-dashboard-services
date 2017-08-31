package com.ibm.usaa.repository;

import java.util.Map;

import com.ibm.usaa.repository.entity.IntervieweeVO;

public interface IntervieweeDAO {
	
	public Map<String, IntervieweeVO> getAllInterviewees();
	public IntervieweeVO getIntervieweeById(String id);
	public boolean addInterviewee(IntervieweeVO intervieweeVO);
	public boolean updateInterviewee(IntervieweeVO intervieweeVO);
	
}
