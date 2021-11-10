package br.com.willian.contatos.controller;

import br.com.willian.contatos.controller.dto.ContatoDTO;
import br.com.willian.contatos.controller.dto.ContatoRequest;
import br.com.willian.contatos.model.Contato;
import br.com.willian.contatos.repository.ContatoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoRepository contatoRepository;

    public ContatoController(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @GetMapping("/")
    public List<ContatoDTO> findAll(){
        var contatos = contatoRepository.findAll();
        return contatos.stream().map(ContatoDTO::converter).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ContatoDTO findById(@PathVariable("id") Long id) throws Exception {
        var contato = contatoRepository.findById(id);
        if(contato.isPresent()){
            var contact = contato.get();
            return ContatoDTO.converter(contact);
        }else{
            throw new Exception("Este Contato não Existe" + id);
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoDTO createContact(@RequestBody ContatoRequest contato) throws Exception {
        var email = contatoRepository.findContatoByEmail(StringUtils.deleteWhitespace(contato.getEmail()));
        var telefone = contatoRepository.findContatoByTelefone(StringUtils.deleteWhitespace(contato.getTelefone()));
        if (email.isPresent() || telefone.isPresent()){
            throw new Exception("Este E-mail ou Telefone já está cadastrado no nosso sistema");
        }else{
            var contact = new Contato();
            contact.setNome(contato.getNome());
            contact.setEmail(contato.getEmail());
            contact.setTelefone(contato.getTelefone());
            contatoRepository.save(contact);
            return ContatoDTO.converter(contact);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoDTO updateContact(@PathVariable("id") Long id, @RequestBody ContatoRequest contato) throws Exception {
        var contact = contatoRepository.findById(id);
        if(contact.isPresent()){
            var newContact = contact.get();
            newContact.setNome(contato.getNome());
            newContact.setEmail(contato.getEmail());
            newContact.setTelefone(contato.getTelefone());
            contatoRepository.save(newContact);
            return ContatoDTO.converter(newContact);
        }else{
            throw new Exception("Este Contato não Existe");
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteContact(@PathVariable("id") Long id) throws Exception {
        boolean existe = contatoRepository.existsById(id);
        if(!existe){
            throw new Exception("Esta Tarefa não Existe");
        }
        contatoRepository.deleteById(id);
        return "Tarefa deletada com sucesso!";
    }
}
