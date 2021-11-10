import { mount } from "@vue/test-utils"
import App from "../App.vue"

describe("APP", () => {

    test('Verifica se é uma instância do Vue', () => {
        const wrapper = mount(App);
        expect(wrapper.vm).toBeTruthy();
    })

    test('Verificar o Texto do H1', () => {
        const wrapper = mount(App);
        const text = wrapper.find('h1');

        expect(text.text()).toContain('Tarefas');
    })

    test('Verifica a mudança e estado do componente', () => {
        const wrapper = mount(App, {
            propsData: {
                tasks: [
                    {name: 'lavar a louça'},
                    {name: 'lavar o carro'}
                ]
            }
        });
        wrapper.vm.tasks = [{name: 'lavar a louça'}, {name: 'lavar o carro'}]
        expect(wrapper.vm.tasks.length).toBe(2)
    })

})