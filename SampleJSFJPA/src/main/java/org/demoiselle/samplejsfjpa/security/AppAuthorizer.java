package org.demoiselle.samplejsfjpa.security;

import br.gov.frameworkdemoiselle.security.Authorizer;
import javax.faces.bean.SessionScoped;

@SessionScoped
public class AppAuthorizer implements Authorizer {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean hasRole(String role) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasPermission(String resource, String operation) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
