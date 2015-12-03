package org.demoiselle.samplejsfjpa.view;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import org.demoiselle.samplejsfjpa.base.GenericMB;
import org.demoiselle.samplejsfjpa.business.BookmarkBC;
import org.demoiselle.samplejsfjpa.domain.Bookmark;

@ViewController
@NextView("/bookmark-edit.xhtml")
@PreviousView("/bookmark-list.xhtml")
public class BookmarkMB extends GenericMB<Bookmark, Long, BookmarkBC> {

    private static final long serialVersionUID = 1L;

}
