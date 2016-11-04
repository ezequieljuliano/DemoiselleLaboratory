package org.demoiselle.jsfjpa.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import org.demoiselle.jsfjpa.template.CrudBC;

import org.demoiselle.jsfjpa.domain.Bookmark;
import org.demoiselle.jsfjpa.persistence.BookmarkDAO;

@BusinessController
public class BookmarkBC extends CrudBC<Bookmark, Long, BookmarkDAO> {

    private static final long serialVersionUID = 1L;

    @Startup
    @Transactional
    public void load() {
        if (findAll().isEmpty()) {
            insert(new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Demoiselle SourceForge", "http://sf.net/projects/demoiselle"));
            insert(new Bookmark("Twitter", "http://twitter.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Blog", "http://blog.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Wiki", "http://wiki.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Forum", "http://forum.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("SVN", "http://svn.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Maven", "http://repository.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Downloads", "http://download.frameworkdemoiselle.gov.br"));
        }
    }

}
