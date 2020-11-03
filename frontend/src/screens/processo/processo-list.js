import React from 'react';
import {Button, Col, Input, Modal, Row, Space, Table} from 'antd';
import {TagStatus} from '../../components';
import Layout from '../layout';
import {AuthService, EnumService, ProcessoService} from '../../services';
import {Menus, Roles, Routes} from '../../constants';
import {Utils} from '../../utils';
import {UserAddOutlined} from '@ant-design/icons';
import {t, Trans} from '@lingui/macro';
import {i18n} from '@lingui/core';

import './processo-list.css';

const ProcessoList = ({ history }) => {
  const isPodeEditar = AuthService.hasRole(Roles.TRIADOR);

  const [processos, setProcessos] = React.useState([]);
  const [statusFilter, setStatusFilter] = React.useState([]);
  const [filtro, setFiltro] = React.useState(null);
  const [loading, setLoading] = React.useState(true);

  React.useEffect(() => {
    EnumService.getStatusProcesso().then(({ data }) => {
      setStatusFilter(Utils.enumsToFilter(data));
      ProcessoService.getTodos().then(({ data }) => {
        setProcessos(data);
        setLoading(false);
      });
    });
  }, [setLoading, setProcessos, setStatusFilter]);

  const onAdicionar = () => {
    const route = Routes.PROCESSO_ADICIONAR;
    history.push(route.path);
  };

  const onEditar = processo => {
    const route = Routes.PROCESSO_EDITAR;
    const path = Utils.formatRoutePath(route, [processo.id]);
    history.push(path);
  };

  const onConfirmarExcluir = processo => {
    Modal.confirm({
      title: i18n._(t`Confirmação`),
      content: i18n._(t`Tem certeza que deseja excluir o processo?`),
      okText: i18n._(t`Sim`),
      cancelText: i18n._(t`Cancelar`),
      onOk: () => onExcluir(processo),
    });
  };

  const onExcluir = processo => {
    ProcessoService.deletar(processo.id).then(() => {
      setProcessos(Utils.dataDelete(processo, processos));
    });
  };

  const onVisualizar = processo => {
    const route = Routes.PROCESSO_VISUALIZAR;
    const path = Utils.formatRoutePath(route, [processo.id]);
    history.push(path);
  };

  const onMenuBack = () => {
    const route = Routes.PROCESSO_LISTAR;
    history.push(route.path);
  };

  return (
    <Layout
      title={i18n._(t`Processos`)}
      subtitle={i18n._(t`Listar`)}
      menuKey={Menus.PROCESSO_LISTAR.key}
      onBack={onMenuBack}>
      <Row className="processo-list">
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
                dataSource={Utils.dataFilter(filtro, processos)}
                loading={loading}>
                <Table.Column
                  key="assunto"
                  title={i18n._(t`Assunto`)}
                  dataIndex="assunto"
                />

                <Table.Column
                  key="nomesInteressados"
                  title={i18n._(t`Interessados`)}
                  dataIndex="nomesInteressados"
                />

                <Table.Column
                  key="status"
                  title={i18n._(t`Status`)}
                  dataIndex="status"
                  filters={statusFilter}
                  onFilter={(value, { status: { chave } }) => chave === value}
                  render={status => <TagStatus status={status} />}
                />

                <Table.Column
                  key="criacao"
                  title={i18n._(t`Criação`)}
                  dataIndex="criacao"
                />

                <Table.Column
                  title={i18n._(t`Acões`)}
                  dataIndex="acoes"
                  key="acoes"
                  render={(value, processo) => (
                    <Space size="1">
                      {isPodeEditar && (
                        <Button
                          disabled={!processo.editavel}
                          type="link"
                          onClick={() => onEditar(processo)}
                          htmlType="link">
                          <Trans>Editar</Trans>
                        </Button>
                      )}

                      {isPodeEditar && (
                        <Button
                          disabled={!processo.editavel}
                          type="link"
                          onClick={() => onConfirmarExcluir(processo)}
                          htmlType="link">
                          <Trans>Excluir</Trans>
                        </Button>
                      )}

                      <Button
                        type="link"
                        onClick={() => onVisualizar(processo)}
                        htmlType="link">
                        <Trans>Visualizar</Trans>
                      </Button>
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

export default ProcessoList;
