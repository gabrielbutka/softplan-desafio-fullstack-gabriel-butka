import {Routes} from './index';
import {i18n} from '@lingui/core';
import {t} from '@lingui/macro';
import {FileAddOutlined, FileTextOutlined, UserAddOutlined, UserOutlined,} from '@ant-design/icons';

const Menus = {
  USUARIO_LISTAR: {
    key: 'usuario-listar',
    route: Routes.USUARIO_LISTAR,
    title: i18n._(t`Listar Usuários`),
    icon: <UserAddOutlined />,
  },
  USUARIO_ADICIONAR: {
    key: 'usuario-adicionar',
    route: Routes.USUARIO_ADICIONAR,
    title: i18n._(t`Adicionar Usuário`),
    icon: <UserOutlined />,
  },
  PROCESSO_LISTAR: {
    key: 'processo-listar',
    route: Routes.PROCESSO_LISTAR,
    title: i18n._(t`Listar Processos`),
    icon: <FileTextOutlined />,
  },
  PROCESSO_ADICIONAR: {
    key: 'processo-adicionar',
    route: Routes.PROCESSO_ADICIONAR,
    title: i18n._(t`Adicionar Processo`),
    icon: <FileAddOutlined />,
  },
};

export default Menus;
