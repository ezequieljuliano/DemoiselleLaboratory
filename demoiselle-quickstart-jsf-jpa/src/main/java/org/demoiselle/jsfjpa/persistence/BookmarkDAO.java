package org.demoiselle.jsfjpa.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import org.demoiselle.jsfjpa.template.CrudDAO;

import org.demoiselle.jsfjpa.domain.Bookmark;

@PersistenceController
public class BookmarkDAO extends CrudDAO<Bookmark, Long> {
	
	private static final long serialVersionUID = 1L;
	
}
