package org.demoiselle.jsfjpa.view;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import org.demoiselle.jsfjpa.template.CrudMB;
import org.demoiselle.jsfjpa.business.UserBC;
import org.demoiselle.jsfjpa.domain.User;

@ViewController
@NextView("/user-edit.xhtml")
@PreviousView("/user-list.xhtml")
public class UserMB extends CrudMB<User, Long, UserBC> {

    private static final long serialVersionUID = 1L;

    private String newPassword = "";

    public UserMB() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void changePassword() {
        try {
            getBusiness().changePassword(getBean(), getNewPassword());
            setNewPassword("");
            addInformationMessage("Senha alterada com sucesso!");
        } catch (Exception ex) {
            addWarningMessage(ex.getMessage());
        }
    }

}
