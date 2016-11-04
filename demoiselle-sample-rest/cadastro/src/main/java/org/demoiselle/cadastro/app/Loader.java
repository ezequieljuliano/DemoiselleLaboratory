package org.demoiselle.cadastro.app;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import javax.inject.Inject;
import org.demoiselle.cadastro.rest.entity.Pessoa;
import org.demoiselle.cadastro.rest.persistence.PessoaDAO;

@Transactional
public class Loader {

    @Inject
    private PessoaDAO pessoaDAO;

    @Startup
    public void load() {
        Pessoa pessoa;

        pessoa = new Pessoa();
        pessoa.setNome("John Malkovich");
        pessoa.setEmail("john.malkovich@gmail.com");
        pessoa.setTelefone("(71) 1234-5678");
        pessoaDAO.insert(pessoa);

        pessoa = new Pessoa();
        pessoa.setNome("Cleverson Sacramento");
        pessoa.setEmail("cleverson.sacramento@serpro.gov.br");
        pessoa.setTelefone("(71) 5555-6666");
        pessoaDAO.insert(pessoa);

        pessoa = new Pessoa();
        pessoa.setNome("Luciano Borges");
        pessoa.setEmail("luciano.borges@serpro.gov.br");
        pessoa.setTelefone("(71) 0000-9999");
        pessoaDAO.insert(pessoa);
    }

}
