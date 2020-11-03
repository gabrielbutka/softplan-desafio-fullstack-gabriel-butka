import React from 'react';
import {Button, Form, Input, InputNumber, Select, Space} from 'antd';
import Layout from '../layout';
import {ProcessoService, UsuarioService} from '../../services';
import {Loading, TagStatus} from '../../components';
import {i18n} from '@lingui/core';
import {t, Trans} from '@lingui/macro';
import {Menus, Routes} from '../../constants';

import './processo-form.css';

const ProcessoForm = ({ match, history }) => {
  const id = match.params.id;

  const isNew = !Boolean(id);

  const formRef = React.createRef();

  const [processo, setProcesso] = React.useState(null);
  const [usuarios, setUsuarios] = React.useState([]);
  const [loading, setLoading] = React.useState(true);

  React.useEffect(() => {
    UsuarioService.getFinalizadores().then(({ data }) => {
      setUsuarios(data);

      if (!isNew) {
        ProcessoService.getProcesso(id).then(({ data }) => {
          setProcesso(data);
          setLoading(false);
        });
      } else {
        setLoading(false);
      }
    });
  }, [id, isNew, setLoading, setProcesso, setUsuarios]);

  const onSubmeter = formValues => {
    const inputValues = {
      processoId: id,
      assunto: formValues.assunto,
      descricao: formValues.descricao,
      interessados: formValues.interessados,
    };

    ProcessoService.salvar(inputValues).then(() => {
      const route = Routes.PROCESSO_LISTAR;
      history.push(route.path);
    });
  };

  const onMenuBack = () => {
    const route = Routes.PROCESSO_LISTAR;
    history.push(route.path);
  };

  const onResetar = () => {
    formRef.current.resetFields();
  };

  const onFiltrarUsuarios = (value, option) => {
    const lvalue = value.toLowerCase();
    const loption = option.children.toLowerCase();
    return loption.includes(lvalue);
  };

  return (
    <Layout
      title={i18n._(t`Processos`)}
      menuKey={isNew ? Menus.PROCESSO_ADICIONAR.key : Menus.PROCESSO_LISTAR.key}
      subtitle={isNew ? i18n._(t`Adicionar`) : i18n._(t`Editar`)}
      onBack={onMenuBack}>
      <div className="processo-form">
        {!loading && (
          <Form
            ref={formRef}
            layout="horizontal"
            wrapperCol={{ span: 12 }}
            labelCol={{ span: 6 }}
            initialValues={{
              ...processo,
              interessados: Boolean(processo)
                ? processo.interessados.map(({ usuario: { id } }) => id)
                : [],
            }}
            onFinish={onSubmeter}>
            {!isNew && (
              <Form.Item name="id" label={i18n._(t`Processo`)}>
                <InputNumber disabled />
              </Form.Item>
            )}
            <Form.Item
              name="assunto"
              label={i18n._(t`Assunto`)}
              rules={[{ required: true, max: 100, type: 'string' }]}>
              <Input />
            </Form.Item>
            <Form.Item
              name="descricao"
              label={i18n._(t`Descrição`)}
              rules={[{ required: true, max: 1000, type: 'string' }]}>
              <Input.TextArea
                autoSize={{ minRows: 5, maxRows: 10 }}
                allowClear
              />
            </Form.Item>
            {!isNew && (
              <Form.Item label={i18n._(t`Status`)}>
                <TagStatus status={processo.status} />
              </Form.Item>
            )}
            <Form.Item name="interessados" label={i18n._(t`Interessados`)}>
              <Select
                mode="multiple"
                filterOption={onFiltrarUsuarios}
                allowClear>
                {usuarios.map(({ id, nome }) => (
                  <Select.Option key={id} value={id}>
                    {nome}
                  </Select.Option>
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

export default ProcessoForm;
