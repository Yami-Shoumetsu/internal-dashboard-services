/**
 * 
 */
package com.ibm.usaa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.usaa.exception.DuplicateDataException;
import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.repository.ExpertiseRepository;
import com.ibm.usaa.repository.entity.ExpertiseVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class ExpertiseService {

    @Autowired
    private ExpertiseRepository expertiseRepository;

    public List<ExpertiseVO> getExpertises() {
        return expertiseRepository.findAll();
    }

    public ExpertiseVO getExpertise(int id) throws InvalidIdException {
        ExpertiseVO expertiseVO = expertiseRepository.findOne(id);
        if (expertiseVO == null) {
            throw new InvalidIdException(id);
        }
        return expertiseVO;
    }

    public int addExpertise(ExpertiseVO expertiseVO) throws DuplicateDataException {
        int numberOfExistingExpertiseName = expertiseRepository.countByExpertiseNameIgnoreCase(expertiseVO.getExpertiseName());
        if (numberOfExistingExpertiseName != 0) {
            throw new DuplicateDataException();
        }
        expertiseVO = expertiseRepository.save(expertiseVO);
        return expertiseVO.getExpertiseId();
    }

    public void updateExpertise(ExpertiseVO expertiseVO) throws InvalidIdException {
        if (!expertiseRepository.exists(expertiseVO.getExpertiseId())) {
            throw new InvalidIdException(expertiseVO.getExpertiseId());
        }
        expertiseRepository.save(expertiseVO);
    }

    public boolean expertiseExists(int id) {
        return expertiseRepository.exists(id);
    }

}
