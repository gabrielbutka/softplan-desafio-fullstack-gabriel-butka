import React from 'react';
import {Button, Col, Divider, Form, Input, List, Row, Space, Typography,} from 'antd';
import Layout from '../layout';
import {AuthService, ProcessoService} from '../../services';
import {Loading, TagStatus} from '../../components';
import {i18n} from '@lingui/core';
import {t, Trans} from '@lingui/macro';
import {Menus, Roles, Routes} from '../../constants';

import './processo-view.css';

const ProcessoView = ({ match, history }) => {
  const id = match.params.id;

  const isPodeInformarParecer = AuthService.hasRole(Roles.FINALIZADOR);
  const isPodeVerPareceres = AuthService.hasRole(Roles.TRIADOR);

  const formRef = React.createRef();

  const [processo, setProcesso] = React.useState(null);
  const [pareceres, setPareceres] = React.useState([]);
  const [loading, setLoading] = React.useState(true);

  React.useEffect(() => {
    ProcessoService.getProcesso(id).then(({ data }) => {
      setProcesso(data);

      if (isPodeVerPareceres) {
        ProcessoService.getPareceres(id).then(({ data }) => {
          setPareceres(data);
          setLoading(false);
        });
      } else {
        setLoading(false);
      }
    });
  }, [id, isPodeVerPareceres, setLoading, setProcesso]);

  const onMenuBack = () => {
    const route = Routes.PROCESSO_LISTAR;
    history.push(route.path);
  };

  const onSubmeter = formValues => {
    const inputValues = {
      processoId: id,
      descricao: formValues.parecer,
    };

    ProcessoService.adicionarParecer(inputValues).then(() => {
      const route = Routes.PROCESSO_LISTAR;
      history.push(route.path);
    });
  };

  const onResetar = () => {
    formRef.current.resetFields();
  };

  return (
    <Layout
      title={i18n._(t`Processos`)}
      menuKey={Menus.PROCESSO_LISTAR.key}
      subtitle={i18n._(t`Visualizar`)}
      onBack={onMenuBack}>
      {!loading && (
        <div className="processo-view">
          <Row>
            <Col xs={24}>
              <Row gutter={[16, 8]}>
                <Col>
                  <Typography.Title type="secondary" level={4}>
                    {i18n._(t`Processo: #${processo.id}`)}
                  </Typography.Title>
                </Col>
                <Col>
                  <TagStatus status={processo.status} />
                </Col>
              </Row>
              <Row>
                <Col xs={24}>
                  <Typography.Paragraph type="secondary">
                    {i18n._(t`Por: ${processo.usuario.nome}`)}
                  </Typography.Paragraph>
                </Col>
              </Row>
              <Row>
                <Col xs={24}>
                  <Typography.Title type="primary" level={3}>
                    {processo.assunto}
                  </Typography.Title>
                </Col>
              </Row>
              <Row>
                <Col xs={24}>
                  <Typography.Paragraph type="primary">
                    {processo.descricao}
                  </Typography.Paragraph>
                </Col>
              </Row>
              <Row>
                <Col xs={24}>
                  <Typography.Title type="secondary" level={5}>
                    <Trans>Interessados</Trans>
                  </Typography.Title>
                  <Space align="top" split={<Divider type="vertical" />}>
                    {processo.interessados.map(interessado => (
                      <Typography.Paragraph
                        type="primary"
                        delete={!interessado.parecerPendente}>
                        {interessado.usuario.nome}
                      </Typography.Paragraph>
                    ))}
                  </Space>
                </Col>
              </Row>
            </Col>
          </Row>

          <Divider />

          {isPodeInformarParecer && (
            <Row>
              <Col xs={24}>
                <Form ref={formRef} layout="vertical" onFinish={onSubmeter}>
                  <Form.Item
                    name="parecer"
                    label={i18n._(t`Seu parecer sobre o processo`)}
                    rules={[{ required: true, max: 1000 }]}>
                    <Input.TextArea
                      autoSize={{ minRows: 5, maxRows: 10 }}
                      allowClear
                    />
                  </Form.Item>
                  <Form.Item>
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
              </Col>
            </Row>
          )}

          {isPodeVerPareceres && (
            <Row>
              <Col xs={24}>
                <Row>
                  <Col xs={24}>
                    <Typography.Title type="secondary" level={4}>
                      <Trans>Pareceres</Trans>
                    </Typography.Title>
                  </Col>
                </Row>
                <Row>
                  <Col xs={24}>
                    <List
                      itemLayout="vertical"
                      dataSource={pareceres}
                      renderItem={parecer => (
                        <List.Item>
                          <List.Item.Meta
                            title={parecer.usuario.nome}
                            description={parecer.criacao}
                          />
                          <Typography.Paragraph>
                            {parecer.descricao}
                          </Typography.Paragraph>
                        </List.Item>
                      )}
                    />
                  </Col>
                </Row>
              </Col>
            </Row>
          )}
        </div>
      )}

      {loading && <Loading />}
    </Layout>
  );
};

export default ProcessoView;
