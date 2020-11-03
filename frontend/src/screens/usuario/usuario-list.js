import React from 'react';
import {Button, Col, Input, Modal, Row, Space, Table} from 'antd';
import Layout from '../layout';
import {UsuarioService} from '../../services';
import {Menus, Routes} from '../../constants';
import {Utils} from '../../utils';
import {UserAddOutlined} from '@ant-design/icons';
import {t, Trans} from '@lingui/macro';
import {i18n} from '@lingui/core';
import {TagSimNao} from '../../components';

import './usuario-list.css';

const UsuarioList = ({ history }) => {
  const [usuarios, setUsuarios] = React.useState([]);
  const [filtro, setFiltro] = React.useState(null);
  const [loading, setLoading] = React.useState(true);

  React.useEffect(() => {
    UsuarioService.getTodos().then(({ data }) => {
      setUsuarios(data);
      setLoading(false);
    });
  }, [setLoading, setUsuarios]);

  const onAtivar = usuario => {
    UsuarioService.ativar(usuario.id).then(({ data }) => {
      setUsuarios(Utils.dataReplace(data, usuarios));
    });
  };

  const onConfirmarAtivar = usuario => {
    Modal.confirm({
      title: i18n._(t`Confirmação`),
      content: i18n._(t`Tem certeza que deseja ativar o usuário?`),
      okText: i18n._(t`Sim`),
      cancelText: i18n._(t`Cancelar`),
      onOk: () => onAtivar(usuario),
    });
  };

  const onInativar = usuario => {
    UsuarioService.inativar(usuario.id).then(({ data: usuario }) => {
      setUsuarios(Utils.dataReplace(usuario, usuarios));
    });
  };

  const onConfirmarInativar = usuario => {
    Modal.confirm({
      title: i18n._(t`Confirmação`),
      content: i18n._(t`Tem certeza que deseja inativar o usuário?`),
      okText: i18n._(t`Sim`),
      cancelText: i18n._(t`Cancelar`),
      onOk: () => onInativar(usuario),
    });
  };

  const onAdicionar = () => {
    const route = Routes.USUARIO_ADICIONAR;
    history.push(route.path);
  };

  const onEditar = usuario => {
    const route = Routes.USUARIO_EDITAR;
    const path = Utils.formatRoutePath(route, [usuario.id]);
    history.push(path);
  };

  const onMenuBack = () => {
    const route = Routes.USUARIO_LISTAR;
    history.push(route.path);
  };

  return (
    <Layout
      title={i18n._(t`Usuários`)}
      subtitle={i18n._(t`Listar`)}
      menuKey={Menus.USUARIO_LISTAR.key}
      onBack={onMenuBack}>
      <Row className="usuario-list">
        <Col xs={24}>
          <Row gutter={[16, 24]}>
            <Col xs={16} md={8}>
              <Input.Search
                placeholder={i18n._(t`Filtrar`)}
                onSearch={filtro => setFiltro(filtro)}
                enterButton
                allowClear
              />
            </Col>
            <Col xs={8} md={4}>
              <Button
                onClick={onAdicionar}
                type="primary"
                htmlType="button"
                icon={<UserAddOutlined />}
                block>
                <Trans>Adicionar</Trans>
              </Button>
            </Col>
          </Row>
          <Row>
            <Col xs={24}>
              <Table
                dataSource={Utils.dataFilter(filtro, usuarios)}
                loading={loading}>
                <Table.Column
                  key="nome"
                  title={i18n._(t`Nome`)}
                  dataIndex="nome"
                />

                <Table.Column
                  key="email"
                  title={i18n._(t`E-mail`)}
                  dataIndex="email"
                />

                <Table.Column
                  key="tipo"
                  title={i18n._(t`Tipo`)}
                  dataIndex="tipo"
                  render={value => value.descricao}
                />

                <Table.Column
                  key="ativo"
                  title={i18n._(t`Ativo`)}
                  dataIndex="ativo"
                  render={value => <TagSimNao value={value} />}
                />

                <Table.Column
                  key="acoes"
                  title={i18n._(t`Acões`)}
                  dataIndex="acoes"
                  render={(value, usuario) => (
                    <Space size="1">
                      <Button
                        type="link"
                        onClick={() => onEditar(usuario)}
                        htmlType="link">
                        <Trans>Editar</Trans>
                      </Button>

                      {usuario && usuario.ativo && (
                        <Button
                          type="link"
                          onClick={() => onConfirmarInativar(usuario)}
                          htmlType="link">
                          <Trans>Inativar</Trans>
                        </Button>
                      )}

                      {usuario && !usuario.ativo && (
                        <Button
                          type="link"
                          onClick={() => onConfirmarAtivar(usuario)}
                          htmlType="link">
                          <Trans>Ativar</Trans>
                        </Button>
                      )}
                    </Space>
                  )}
                />
              </Table>
            </Col>
          </Row>
        </Col>
      </Row>
    </Layout>
  );
};

export default UsuarioList;
