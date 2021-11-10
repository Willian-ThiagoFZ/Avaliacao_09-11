import { http } from './config'

export default {

    listar: () => {
        return http.get('/')
    },

    salvar: (contato) => {
        return http.post('/', contato)
    },

    atualizar: (contato) => {
        return http.put(`/${contato.id}`, contato)
    },

    deletar: (contato) => {
        return http.delete(`/${contato.id}`)
    }


}