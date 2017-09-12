/**
 * 
 */
package com.ibm.usaa.exception;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class InvalidDependentIdException extends Exception {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final String dependentName;

    public InvalidDependentIdException(int id, String dependentName) {
        super();
        this.id = id;
        this.dependentName = dependentName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return "Invalid ID of " + dependentName + ": " + id;
    }

}
