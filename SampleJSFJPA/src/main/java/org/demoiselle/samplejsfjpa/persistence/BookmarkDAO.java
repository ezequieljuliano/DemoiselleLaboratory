package org.demoiselle.samplejsfjpa.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import org.demoiselle.samplejsfjpa.base.GenericDAO;

import org.demoiselle.samplejsfjpa.domain.Bookmark;

@PersistenceController
public class BookmarkDAO extends GenericDAO<Bookmark, Long> {
	
	private static final long serialVersionUID = 1L;
	
}
