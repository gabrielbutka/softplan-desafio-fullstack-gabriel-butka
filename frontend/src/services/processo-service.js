import axios from 'axios';

class ProcessoService {
  todos() {
    return axios.get('processo/todos');
  }

  processo(id) {
    return axios.get(`/processo/${id}`);
  }

  pareceres(id) {
    return axios.get(`/processo/pareceres/${id}`);
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
