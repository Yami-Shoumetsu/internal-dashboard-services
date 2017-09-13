package com.ibm.usaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.usaa.repository.entity.IntervieweeVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public interface IntervieweeRepository extends JpaRepository<IntervieweeVO, Integer> {

    public List<IntervieweeVO> findByExpertiseExpertiseName(String expertiseName);

}
