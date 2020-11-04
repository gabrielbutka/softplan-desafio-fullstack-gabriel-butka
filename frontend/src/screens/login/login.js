import React from 'react';
import {Button, Col, Form, Input, Row, Space} from 'antd';
import {Roles, Routes} from '../../constants';
import {Redirect} from 'react-router';
import {AuthService} from '../../services';
import {t, Trans} from '@lingui/macro';
import {i18n} from '@lingui/core';

import './login.css';

const Login = () => {
  const formRef = React.createRef();

  const [redirect, setRedirect] = React.useState(false);

  const onSubmeter = ({ email, senha }) => {
    AuthService.login(email, senha).then(response => {
      setRedirect(Boolean(response));
    });
  };

  const onResetar = () => {
    formRef.current.resetFields();
  };

  if (redirect && AuthService.hasRole(Roles.ADMINISTRADOR)) {
    return <Redirect to={Routes.USUARIO_LISTAR.path} />;
  }

  if (redirect) {
    return <Redirect to={Routes.PROCESSO_LISTAR.path} />;
  }

  return (
    <div className="login">
      <Row type="flex" justify="center" align="middle">
        <Col xs={16} md={6}>
          <div className="login-content">
            <Row>
              <Col xs={24}>
                <div className="login-logo">
                  <img src="logo-positiva.svg" alt="Softplan" />
                </div>
              </Col>
            </Row>
            <Row>
              <Col xs={24}>
                <Form ref={formRef} layout="vertical" onFinish={onSubmeter}>
                  <Form.Item
                    name="email"
                    label={i18n._(t`E-mail`)}
                    rules={[{ required: true, type: 'email' }]}>
                    <Input />
                  </Form.Item>
                  <Form.Item
                    name="senha"
                    label={i18n._(t`Senha`)}
                    rules={[{ required: true, type: 'string' }]}>
                    <Input.Password />
                  </Form.Item>
                  <Form.Item>
                    <Space>
                      <Button type="primary" htmlType="submit">
                        <Trans>Entrar</Trans>
                      </Button>
                      <Button htmlType="button" onClick={onResetar}>
                        <Trans>Resetar</Trans>
                      </Button>
                    </Space>
                  </Form.Item>
                </Form>
              </Col>
            </Row>
          </div>
        </Col>
      </Row>
    </div>
  );
};

export default Login;
