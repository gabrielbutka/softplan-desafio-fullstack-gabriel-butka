import axios from 'axios';

class AuthService {
  login(email, senha) {
    return axios.post('/auth/login', { email, senha }).then(({ data }) => {
      if (Boolean(data)) {
        const token = btoa(email + ':' + senha);
        const user = { ...data, token: token };
        const json = JSON.stringify(user);
        localStorage.setItem('user', json);
      }
      return Boolean(data);
    });
  }

  logout() {
    localStorage.removeItem('user');
  }

  isLogged() {
    return Boolean(this.getUser());
  }

  getToken() {
    return !this.isLogged() ? null : this.getUser().token;
  }

  hasRole(roles) {
    return this.getRoles().some(role => roles.includes(role));
  }

  getRoles() {
    return this.getUser().authorities.map(({ authority }) => authority);
  }

  getUser() {
    return JSON.parse(localStorage.getItem('user'));
  }
}

export default new AuthService();
