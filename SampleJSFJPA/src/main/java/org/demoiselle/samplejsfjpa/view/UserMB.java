package org.demoiselle.samplejsfjpa.view;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import org.demoiselle.samplejsfjpa.base.GenericMB;
import org.demoiselle.samplejsfjpa.business.UserBC;
import org.demoiselle.samplejsfjpa.domain.User;

@ViewController
@NextView("/user-edit.xhtml")
@PreviousView("/user-list.xhtml")
public class UserMB extends GenericMB<User, Long, UserBC> {

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
            addMessageContext("Senha alterada com sucesso!", SeverityType.INFO);
        } catch (Exception ex) {
            addMessageContext(ex.getMessage(), SeverityType.WARN);
        }
    }

}
