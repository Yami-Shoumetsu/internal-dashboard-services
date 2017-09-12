/**
 * 
 */
package com.ibm.usaa.exception;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class InvalidIdException extends Exception {

    private static final long serialVersionUID = 1L;

    private final int id;

    public InvalidIdException(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return "Invalid ID: " + id;
    }

}
