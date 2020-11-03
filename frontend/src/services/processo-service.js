import axios from 'axios';

class ProcessoService {
  getTodos() {
    return axios.get('processo/todos');
  }

  getProcesso(id) {
    return axios.get(`/processo/${id}`);
  }

  getPareceres(id) {
    return axios.get(`/processo/pareceres/${id}`);
  }

  deletar(id) {
    return axios.delete(`/processo/deletar/${id}`);
  }

  salvar(values) {
    return values.processoId ? this.atualizar(values) : this.adicionar(values);
  }

  adicionar(values) {
    return axios.post('/processo/adicionar', values);
  }

  atualizar(values) {
    return axios.post('/processo/atualizar', values);
  }

  adicionarParecer(values) {
    return axios.post('/processo/adicionar/parecer', values);
  }
}

export default new ProcessoService();
