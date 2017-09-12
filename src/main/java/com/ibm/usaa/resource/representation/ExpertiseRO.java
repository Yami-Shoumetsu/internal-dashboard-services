/**
 * 
 */
package com.ibm.usaa.resource.representation;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class ExpertiseRO {

    private Integer id;

    @NotNull
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
