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

    private final Integer id;
    private final String idString;

    public InvalidIdException(int id) {
        super();
        this.id = id;
        this.idString = null;
    }

    public InvalidIdException(String id) {
        super();
        this.idString = id;
        this.id = null;
    }

    public Integer getId() {
        return id;
    }

    public String getIdString() {
        return idString;
    }

    @Override
    public String getMessage() {
        if (id != null) {
            return "Invalid ID: " + id;
        } else if (idString != null) {
            return "Invalid ID: " + idString;
        }
        return "Invalid ID";
    }

}
