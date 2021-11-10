import { mount } from "@vue/test-utils"
import App from "../App.vue"

const wrapper = mount(App);
describe("APP", () => {

    test('Verifica se é uma instância do Vue', () => {
        expect(wrapper.vm).toBeTruthy();
    }),

    test('Verificar o Texto do H1', () => {
        const text = wrapper.find('a');
        expect(text.text()).toContain('Lista de Contatos');
    }),

    test('Adiciona um contato', () => {
        wrapper.vm.$data.contato.nome = "Will"
        wrapper.vm.$data.contato.email = "wil@hotmail.com"
        wrapper.vm.$data.contato.telefone = "1326548"

        expect(wrapper.vm.$data.contato.nome).toBe('Will')
    })

})