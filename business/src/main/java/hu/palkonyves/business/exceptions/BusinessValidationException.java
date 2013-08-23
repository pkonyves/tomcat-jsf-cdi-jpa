package hu.palkonyves.business.exceptions;

public class BusinessValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessValidationException(String cause) {
	super(cause);
    }

    public BusinessValidationException(String cause, Throwable e) {
	super(cause, e);
    }

}
