import axios from 'axios';

class UsuarioService {
  getTodos() {
    return axios.get('/usuario/todos');
  }

  getFinalizadores() {
    return axios.get('/usuario/finalizadores');
  }

  getUsuario(id) {
    return axios.get(`/usuario/${id}`);
  }

  ativar(id) {
    return axios.get(`/usuario/ativar/${id}`);
  }

  inativar(id) {
    return axios.get(`/usuario/inativar/${id}`);
  }

  salvar(values) {
    return values.usuarioId ? this.atualizar(values) : this.adicionar(values);
  }

  adicionar(values) {
    return axios.post('/usuario/adicionar', values);
  }

  atualizar(values) {
    return axios.post('/usuario/atualizar', values);
  }
}

export default new UsuarioService();
