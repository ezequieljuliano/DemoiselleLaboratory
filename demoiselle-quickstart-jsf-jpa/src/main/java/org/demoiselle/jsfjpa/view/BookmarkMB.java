package org.demoiselle.jsfjpa.view;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import org.demoiselle.jsfjpa.template.CrudMB;
import org.demoiselle.jsfjpa.business.BookmarkBC;
import org.demoiselle.jsfjpa.domain.Bookmark;

@ViewController
@NextView("/bookmark-edit.xhtml")
@PreviousView("/bookmark-list.xhtml")
public class BookmarkMB extends CrudMB<Bookmark, Long, BookmarkBC> {

    private static final long serialVersionUID = 1L;

}
