import { mount } from "@vue/test-utils"
import NewTask from "../components/NewTask.vue"

describe("NEWTASK COMPONENT", () => {

    test('Verifica se é uma instância do Vue', () => {
        const wrapper = mount(NewTask);
        expect(wrapper.vm).toBeTruthy();
    })

    test('Verifica a mudança e estado do componente', () => {
        const wrapper = mount(NewTask);
        wrapper.vm.name = 'lavar a louça'
        expect(wrapper.vm.name).toBe('lavar a louça')
    })

    test('Adicionar uma Task', () => {
        const wrapper = mount(NewTask);
        const button = wrapper.find('button')
        wrapper.vm.name = 'lavar louça'
        button.trigger('click')
        expect(wrapper.vm.name).toBe('')
    })

})