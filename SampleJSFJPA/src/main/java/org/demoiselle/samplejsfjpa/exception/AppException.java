package org.demoiselle.samplejsfjpa.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(rollback = true, severity = SeverityType.INFO)
public class AppException extends Exception {

    public AppException(String message) {
        super(message);
    }

}
