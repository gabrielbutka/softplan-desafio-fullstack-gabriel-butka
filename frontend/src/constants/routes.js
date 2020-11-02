import {Roles} from './index';
import {Login, ProcessoForm, ProcessoList, ProcessoView, UsuarioForm, UsuarioList,} from '../screens';

const Routes = {
  LOGIN: {
    path: '/',
    roles: [],
    component: Login,
  },
  USUARIO_LISTAR: {
    path: '/usuarios',
    roles: [Roles.ADMINISTRADOR],
    component: UsuarioList,
  },
  USUARIO_ADICIONAR: {
    path: '/usuario/adicionar',
    roles: [Roles.ADMINISTRADOR],
    component: UsuarioForm,
  },
  USUARIO_EDITAR: {
    path: '/usuario/:id',
    roles: [Roles.ADMINISTRADOR],
    component: UsuarioForm,
  },
  PROCESSO_LISTAR: {
    path: '/processos',
    roles: [Roles.TRIADOR, Roles.FINALIZADOR],
    component: ProcessoList,
  },
  PROCESSO_ADICIONAR: {
    path: '/processo/adicionar',
    roles: [Roles.TRIADOR],
    component: ProcessoForm,
  },
  PROCESSO_EDITAR: {
    path: '/processo/:id',
    roles: [Roles.TRIADOR],
    component: ProcessoForm,
  },
  PROCESSO_VISUALIZAR: {
    path: '/processo/visualizar/:id',
    roles: [Roles.TRIADOR, Roles.FINALIZADOR],
    component: ProcessoView,
  },
};

export default Routes;
