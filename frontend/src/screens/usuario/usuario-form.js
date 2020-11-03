import React from 'react';
import {Button, Form, Input, Select, Space} from 'antd';
import Layout from '../layout';
import {EnumService, UsuarioService} from '../../services';
import {Loading} from '../../components';
import {i18n} from '@lingui/core';
import {t, Trans} from '@lingui/macro';
import {Menus, Routes} from '../../constants';

import './usuario-form.css';

const UsuarioForm = ({ match, history }) => {
  const id = match.params.id;

  const formRef = React.createRef();

  const [usuario, setUsuario] = React.useState(null);
  const [tiposUsuario, setTiposUsuario] = React.useState([]);
  const [loading, setLoading] = React.useState(true);

  const isNew = !Boolean(id);

  React.useEffect(() => {
    EnumService.getTiposUsuario().then(({ data }) => {
      setTiposUsuario(data);

      if (!isNew) {
        UsuarioService.getUsuario(id).then(({ data }) => {
          setUsuario(data);
          setLoading(false);
        });
      } else {
        setLoading(false);
      }
    });
  }, [id, isNew, setLoading, setUsuario, setTiposUsuario]);

  const onSubmeter = values => {
    const inputValues = {
      usuarioId: id,
      nome: values.nome,
      email: values.email,
      tipo: values.tipo.chave,
      senha: values.senha,
    };

    UsuarioService.salvar(inputValues).then(() => {
      const route = Routes.USUARIO_LISTAR;
      history.push(route.path);
    });
  };

  const onMenuBack = () => {
    const route = Routes.USUARIO_LISTAR;
    history.push(route.path);
  };

  const onResetar = () => {
    formRef.current.resetFields();
  };

  return (
    <Layout
      title={i18n._(t`Usuários`)}
      menuKey={isNew ? Menus.USUARIO_ADICIONAR.key : Menus.USUARIO_LISTAR.key}
      subtitle={isNew ? i18n._(t`Adicionar`) : i18n._(t`Editar`)}
      onBack={onMenuBack}>
      <div className="usuario-form">
        {!loading && (
          <Form
            ref={formRef}
            layout="horizontal"
            wrapperCol={{ span: 12 }}
            labelCol={{ span: 6 }}
            initialValues={{ ...usuario }}
            onFinish={onSubmeter}>
            <Form.Item
              label={i18n._(t`Nome`)}
              name="nome"
              rules={[{ required: true, max: 100, type: 'string' }]}>
              <Input />
            </Form.Item>
            <Form.Item
              name="email"
              label={i18n._(t`E-mail`)}
              rules={[{ required: true, max: 100, type: 'email' }]}>
              <Input />
            </Form.Item>
            <Form.Item
              name="senha"
              label={i18n._(t`Senha`)}
              rules={[{ required: isNew, max: 60, type: 'string' }]}>
              <Input.Password />
            </Form.Item>
            <Form.Item
              name={['tipo', 'chave']}
              label={i18n._(t`Tipo`)}
              rules={[{ required: true }]}>
              <Select>
                {tiposUsuario.map(({ chave, descricao }) => (
                  <Select.Option value={chave}>{descricao}</Select.Option>
                ))}
              </Select>
            </Form.Item>
            <Form.Item wrapperCol={{ offset: 6, span: 12 }}>
              <Space>
                <Button type="primary" htmlType="submit">
                  <Trans>Salvar</Trans>
                </Button>
                <Button htmlType="button" onClick={onResetar}>
                  <Trans>Resetar</Trans>
                </Button>
              </Space>
            </Form.Item>
          </Form>
        )}

        {loading && <Loading />}
      </div>
    </Layout>
  );
};

export default UsuarioForm;
