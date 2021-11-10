
<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Contatos</a>
      </div>
    </nav>

    <div class="container">

      <ul>
        <li v-for="(erro, index) of errors" :key="index">
          campo <b>{{erro.field}}</b> - {{erro.defaultMessage}}
        </li>
      </ul>


      <form @submit.prevent="salvar">

          <label>Nome</label>
          <input type="text" class="text-white" placeholder="Nome" v-model="contato.nome" required>
          <label>E-mail</label>
          <input type="text" placeholder="example@hotmail.com" v-model="contato.email" required>
          <label>Telefone</label>
          <input type="text" placeholder="99999-9999" v-model="contato.telefone" required>

          <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>

      </form>

      <table>

        <thead>

          <tr>
            <th>NOME</th>
            <th>E-MAIL</th>
            <th>TELEFONE</th>
            <th>OPÇÕES</th>
          </tr>

        </thead>

        <tbody>

          <tr v-for="contato of contatos" :key="contato.id">

            <td>{{ contato.nome }}</td>
            <td>{{ contato.email }}</td>
            <td>{{ contato.telefone }}</td>
            <td>
              <button @click="editar(contato)" class="waves-effect btn-small blue darken-1"><i class="material-icons">create</i></button>
              <button @click="remover(contato)" class="waves-effect btn-small red darken-1"><i class="material-icons">delete_sweep</i></button>
            </td>

          </tr>

        </tbody>
      
      </table>

    </div>

  </div>
</template>

<script>
/* eslint-disable */
import Contato from './services/contato'

export default {
  name: 'app',
  data () {
    return {
      contato:{
		nome:'',
        email: '',
        telefone: '',
      },
      contatos: [],
      errors: []
    }
  },

  mounted(){
    this.listar()
  },

  methods:{
    
    listar(){
      Contato.listar().then(resposta => {
        this.contatos = resposta.data
      })
    },

    salvar(){

		if(!this.contato.id){
			Contato.salvar(this.contato).then(() => {
			  this.contato = {}
			  alert('Cadastrado com sucesso!')
			  this.listar()
			  this.errors = {}
			}).catch(e => {
			  this.errors = e.response.data.errors
			})
		}else{
			Contato.atualizar(this.contato).then(() => {
			  this.contato = {}
			  this.errors = {}
			  alert('Atualizado com sucesso!')
			  this.listar()
			}).catch(e => {
			  this.errors = e.response.data.errors
			})
		}
      

    },

    editar(contato){
      this.contato = contato
    },

    remover(contato){

      if(confirm('Deseja excluir o contato?')){

        Contato.deletar(contato).then(() => {
          this.listar()
          this.errors = {}
        }).catch(e => {
          this.errors = e.response.data.errors
        })

      }

    }
  }

}
</script>

<style>
	body {
		font-family: 'Lato', sans-serif;
		background: linear-gradient(to right, rgb(22, 34, 42), rgb(58, 96, 115));
		color: #FFF;
	}

	#app {
		display: flex;
		flex: 1;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	#app h1 {
		margin-bottom: 5px;
		font-weight: 300;
		font-size: 3rem;
	}

	input{
		color: #FFF;
	}


</style>
