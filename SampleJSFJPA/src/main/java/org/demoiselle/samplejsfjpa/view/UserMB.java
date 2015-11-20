package org.demoiselle.samplejsfjpa.view;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import org.demoiselle.samplejsfjpa.base.GenericMB;
import org.demoiselle.samplejsfjpa.business.UserBC;
import org.demoiselle.samplejsfjpa.domain.User;

@ViewController
@NextView("/user-edit.xhtml")
@PreviousView("/user-list.xhtml")
public class UserMB extends GenericMB<User, Long, UserBC> {

}
