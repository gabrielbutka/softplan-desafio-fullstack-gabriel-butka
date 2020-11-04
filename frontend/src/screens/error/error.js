import React from 'react';
import {Button, Result, Space} from 'antd';
import {Routes} from '../../constants';

import './error.css';

const Error = ({ history }) => (
  <div className="error">
    <Result
      status="error"
      title="Acesso negado ou servidor indisponível!"
      extra={
        <Space>
          <Button
            type="primary"
            onClick={() => history.push(Routes.LOGIN.path)}>
            Entrar Novamente
          </Button>
          <Button type="secondary" onClick={() => history.goBack()}>
            Voltar
          </Button>
        </Space>
      }
    />
  </div>
);

export default Error;
