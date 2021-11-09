package br.com.willian.contatos.controller.dto;

import br.com.willian.contatos.model.Contato;

public class ContatoDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public ContatoDTO(long id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public ContatoDTO( String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public ContatoDTO() {}

    public static ContatoDTO converter(Contato contato){
        var contact = new ContatoDTO();
        contact.setId(contato.getId());
        contact.setNome(contato.getNome());
        contact.setEmail(contato.getEmail());
        contact.setTelefone(contato.getTelefone());
        return contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
