import axios from 'axios';

class EnumService {
  getTiposUsuario() {
    return axios.get('/enum/tiposUsuario');
  }

  getStatusProcesso() {
    return axios.get('/enum/statusProcesso');
  }
}

export default new EnumService();
