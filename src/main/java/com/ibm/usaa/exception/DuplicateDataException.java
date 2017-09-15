/**
 * 
 */
package com.ibm.usaa.exception;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class DuplicateDataException extends Exception {

    private static final long serialVersionUID = 1L;

    public DuplicateDataException() {
        super("Duplicate Data Detected!");
    }

}
