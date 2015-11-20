package org.demoiselle.cadastro.rest;

import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import br.gov.frameworkdemoiselle.NotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import org.demoiselle.cadastro.rest.entity.Pessoa;
import org.demoiselle.cadastro.rest.persistence.PessoaDAO;

@Path("pessoas")
public class PessoaREST {

    @GET
    @Path("{id}")
    @Transactional
    @Produces("application/json")
    public PessoaBody obter(@PathParam("id") String id) throws Exception {
        Pessoa pessoa = PessoaDAO.getInstance().load(Integer.valueOf(id));

        if (pessoa == null) {
            throw new NotFoundException();
        }

        PessoaBody body = new PessoaBody();
        body.nome = pessoa.getNome();
        body.email = pessoa.getEmail();
        body.telefone = pessoa.getTelefone();

        return body;
    }

    @PUT
    @Path("{id}")
    @Transactional
    @ValidatePayload
    @Consumes("application/json")
    public void atualizar(@PathParam("id") Integer id, PessoaBody body) throws Exception {
        PessoaDAO pessoaDAO = PessoaDAO.getInstance();
        Pessoa pessoa = pessoaDAO.load(id);

        if (pessoa == null) {
            throw new NotFoundException();
        }

        pessoa.setNome(body.nome);
        pessoa.setEmail(body.email);
        pessoa.setTelefone(body.telefone);
        pessoaDAO.update(pessoa);
    }

    @PATCH
    @Path("{id}")
    @Transactional
    @ValidatePayload
    @Consumes("application/json")
    public void atualizarParcial(@PathParam("id") Integer id, PessoaPatchBody body) throws Exception {
        PessoaDAO pessoaDAO = PessoaDAO.getInstance();
        Pessoa pessoa = pessoaDAO.load(id);

        if (pessoa == null) {
            throw new NotFoundException();
        }

        if (body.nome != null) {
            pessoa.setNome(body.nome);
        }

        if (body.email != null) {
            pessoa.setEmail(body.email);
        }

        if (body.telefone != null) {
            pessoa.setTelefone(body.telefone);
        }

        pessoaDAO.update(pessoa);
    }

    @POST
    @Transactional
    @ValidatePayload
    @Consumes("application/json")
    @Produces("text/plain")
    public Response inserir(PessoaBody body) {
        Pessoa entity = new Pessoa();
        entity.setNome(body.nome);
        entity.setEmail(body.email);
        entity.setTelefone(body.telefone);

        PessoaDAO.getInstance().insert(entity);

        String id = entity.getId().toString();
        String url = "http://localhost:8080/cadastro/api/pessoas/" + id;
        return Response.status(201).header("Location", url).entity(id).build();
    }

    @XmlRootElement
    public static class PessoaBody {

        @NotEmpty
        @Size(min = 3, max = 50)
        public String nome;

        @Email
        @NotEmpty
        @Size(max = 255)
        public String email;

        @Size(max = 15)
        public String telefone;
    }

    @XmlRootElement
    public static class PessoaPatchBody {

        @Size(min = 3, max = 50)
        public String nome;

        @Email
        @Size(max = 255)
        public String email;

        @Size(max = 15)
        public String telefone;
    }
}
