import axios from 'axios';

class EnumService {
  tiposUsuario() {
    return axios.get('/enum/tiposUsuario');
  }

  statusProcesso() {
    return axios.get('/enum/statusProcesso');
  }
}

export default new EnumService();
