/**
 * 
 */
package com.ibm.usaa.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ibm.usaa.repository.entity.ExpertiseVO;
import com.ibm.usaa.resource.representation.ExpertiseRO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class ExpertiseMapper {

    public static List<ExpertiseRO> mapExpertisesToRepresentationObject(List<ExpertiseVO> expertises) {
        List<ExpertiseRO> expertiseROs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(expertises)) {
            for (ExpertiseVO interviewee : expertises) {
                ExpertiseRO intervieweeRO = mapExpertiseToRepresentationObject(interviewee);
                expertiseROs.add(intervieweeRO);
            }
        }
        return expertiseROs;
    }

    public static ExpertiseRO mapExpertiseToRepresentationObject(ExpertiseVO expertise) {
        if (expertise != null) {
            ExpertiseRO expertiseRO = new ExpertiseRO();
            expertiseRO.setId(expertise.getExpertiseId());
            expertiseRO.setName(expertise.getExpertiseName());
            return expertiseRO;
        }
        return null;
    }

    public static ExpertiseVO mapExpertiseFromRepresentationObject(ExpertiseRO expertiseRO) {
        if (expertiseRO != null) {
            ExpertiseVO expertise = new ExpertiseVO();
            expertise.setExpertiseId(expertiseRO.getId());
            expertise.setExpertiseName(expertiseRO.getName());
            return expertise;
        }
        return null;
    }

}
