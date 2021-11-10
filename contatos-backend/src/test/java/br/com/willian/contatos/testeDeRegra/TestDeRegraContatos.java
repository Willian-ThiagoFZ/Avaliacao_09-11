package br.com.willian.contatos.testeDeRegra;

import br.com.willian.contatos.controller.dto.ContatoRequest;
import br.com.willian.contatos.model.Contato;
import br.com.willian.contatos.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.InternalException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestDeRegraContatos {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ContatoRepository contatoRepository;

    String url = "/contatos/";

    private String retornaNomeAleatorio(){
        Random random = new Random();
        int numeroInteiroAleatorio_0_a_1000 = random.nextInt(1000);
        return "IntegraçãoTestes" + numeroInteiroAleatorio_0_a_1000;
    }

    private String retornaEmailAleatorio(){
        Random random = new Random();
        int numeroInteiroAleatorio_0_a_1000 = random.nextInt(1000);
        return "IntegraçãoTestes" + numeroInteiroAleatorio_0_a_1000 + "@hotmail.com";
    }

    private String retornaTelefoneAleatorio(){
        Random random = new Random();
        Integer telefone = random.nextInt((999999999 - 111111111) + 1) + 100000;
        return Integer.toString(telefone);
    }

    @Test
    @Order(1)
    void shouldGetAllContacts() throws Exception {
        String url = "/contatos/";
        mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void shouldCreateaNewContact() throws Exception {

        ContatoRequest contatoTest = new ContatoRequest(retornaNomeAleatorio(), retornaEmailAleatorio(), retornaTelefoneAleatorio());

        String content = objectMapper.writeValueAsString(contatoTest);

        mockMvc.perform(
                post(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @SneakyThrows
    @Test
    @Order(3)
    void shouldAddContactReturnValidationExceptionIfRepeatedEmail() {
        ContatoRequest contatoTest = new ContatoRequest(retornaNomeAleatorio(),"wil@hotm", retornaTelefoneAleatorio());

        String content = objectMapper.writeValueAsString(contatoTest);

        mockMvc.perform(
                post(url,content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    @Order(4)
    void shouldAddContactReturnValidationExceptionIfRepeatedTelefone() {
        ContatoRequest contatoTest = new ContatoRequest(retornaNomeAleatorio(),retornaEmailAleatorio(), "123465489");

        String content = objectMapper.writeValueAsString(contatoTest);

        mockMvc.perform(
                post(url,content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

}
