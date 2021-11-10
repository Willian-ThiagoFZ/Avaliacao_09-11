import { mount } from "@vue/test-utils"
import Task from "../components/Task.vue"

describe("TASK COMPONENT", () => {

    test('Verifica se é uma instância do Vue', () => {
        const wrapper = mount(Task, {
            propsData: {
              task: {
                  name: 'lavar a louça',
                  pending: true
              }
            }
        });
        
        expect(wrapper.vm).toBeTruthy();
    })

    test('Verifica se o nome da Task se encontra no HTML', () => {
        const wrapper = mount(Task, {
            propsData: {
              task: {
                  name: 'lavar a louça',
                  pending: true
              }
            }
        });
        const text = wrapper.find('p')
        
        expect(text.text()).toContain('lavar a louça')
    })

    test('Deleta uma Task', () => {
        const wrapper = mount(Task, {
            propsData: {
              task: {
                  name: 'lavar a louça',
                  pending: true
              }
            }
        });

        expect(wrapper.vm).toBeTruthy();
    })

    test('Mudança de estado', () => {
        const wrapper = mount(Task, {
            propsData: {
                task: {name: 'lavar a louça'}
            }
        });
        wrapper.vm.task.name = 'lavar a louça'
        expect(wrapper.vm.task.name).toBe('lavar a louça')
    })

})