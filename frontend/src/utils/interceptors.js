import {message as AntMessage} from 'antd';
import {AuthService} from '../services';

const successRequestInterceptor = config => {
  if (AuthService.isLogged()) {
    config.headers.Authorization = 'Basic ' + AuthService.getToken();
    config.headers.post['Access-Control-Allow-Origin'] = '*';
  }
  return config;
};

const successResponseInterceptor = response => {
  const { data } = response;

  if (!Boolean(data)) {
    window.location.href = '/';
  }

  const { message } = data;

  if (Boolean(message)) {
    AntMessage.success(message);
  }

  return data;
};

const errorResponseInterceptor = ({ response }) => {
  const { data } = response;
  const { status, message } = data;

  if (status === 401) {
    window.location.href = '/';
  }

  if (Boolean(message)) {
    AntMessage.error(message);
  }

  return data;
};

const interceptors = {
  request: {
    success: successRequestInterceptor,
    error: undefined,
  },
  response: {
    success: successResponseInterceptor,
    error: errorResponseInterceptor,
  },
};

export default interceptors;
