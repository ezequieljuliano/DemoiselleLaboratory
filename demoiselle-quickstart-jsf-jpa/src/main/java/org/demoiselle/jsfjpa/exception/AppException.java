package org.demoiselle.jsfjpa.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(rollback = true, severity = SeverityType.ERROR)
public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }

}
