import React from 'react';
import axios from 'axios';
import ReactDOM from 'react-dom';
import App from './app';
import {BrowserRouter} from 'react-router-dom';
import {AxiosInterceptors} from './utils';
import {i18n} from '@lingui/core';
import {I18nProvider} from '@lingui/react';
import {messages} from './locales/pt-br/messages.js';
import {ConfigProvider} from 'antd';
import ptBR from 'antd/lib/locale/pt_BR';

import './index.css';

(async function initApp() {
  i18n.load('pt-br', messages);
  i18n.activate('pt-br');

  axios.defaults.baseURL = process.env.REACT_APP_API_URL;

  axios.interceptors.request.use(
    AxiosInterceptors.request.success,
    AxiosInterceptors.request.error
  );

  axios.interceptors.response.use(
    AxiosInterceptors.response.success,
    AxiosInterceptors.response.error
  );
})();

ReactDOM.render(
  <I18nProvider i18n={i18n}>
    <ConfigProvider locale={ptBR}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </ConfigProvider>
  </I18nProvider>,
  document.getElementById('root')
);
