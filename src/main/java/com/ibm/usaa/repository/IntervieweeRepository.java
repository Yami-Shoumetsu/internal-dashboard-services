package com.ibm.usaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibm.usaa.repository.entity.IntervieweeVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public interface IntervieweeRepository extends JpaRepository<IntervieweeVO, Integer> {

    @Query("SELECT i FROM IntervieweeVO i WHERE i.expertise.expertiseName = :expertiseName")
    public List<IntervieweeVO> findByExpertise(@Param("expertiseName") String expertiseName);

}
