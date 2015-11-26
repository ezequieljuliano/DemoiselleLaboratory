package org.demoiselle.cadastro.rest.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.demoiselle.cadastro.rest.entity.Pessoa;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Reflections;
import br.gov.frameworkdemoiselle.util.Strings;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.TypedQuery;

@Transactional
public class PessoaDAO {

    @Inject
    private EntityManager em;

    public static PessoaDAO getInstance() {
        return Beans.getReference(PessoaDAO.class);
    }

    public List<Pessoa> find(String filter, String order) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select p ");
        jpql.append("   from Pessoa p ");
        jpql.append("  where lower(p.nome) like :filter ");
        jpql.append("     or lower(p.email) like :filter ");
        jpql.append("     or p.telefone like :filter ");

        if (!Strings.isEmpty(order)) {
            validate(order);
            jpql.append("  order by ");
            jpql.append("        p." + order + "  asc ");
        }

        TypedQuery<Pessoa> query = em.createQuery(jpql.toString(), Pessoa.class);
        query.setParameter("filter", "%" + (filter == null ? "" : filter.toLowerCase()) + "%");

        return query.getResultList();
    }

    private void validate(String order) {
        for (Field field : Reflections.getNonStaticDeclaredFields(Pessoa.class)) {
            if (field.getName().equalsIgnoreCase(order)) {
                return;
            }
        }

        throw new IllegalArgumentException();
    }

    public void insert(Pessoa pessoa) {
        em.persist(pessoa);
    }

    public void update(Pessoa pessoa) {
        em.merge(pessoa);
    }

    public Pessoa load(Integer id) {
        return em.find(Pessoa.class, id);
    }
}
