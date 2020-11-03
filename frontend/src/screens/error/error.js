import React from 'react';
import {Empty} from 'antd';
import {Routes} from '../../constants';
import Layout from '../layout';

import './error.css';

const Error = ({ history }) => {
  const onMenuBack = () => {
    const route = Routes.LOGIN;
    history.push(route.path);
  };

  return (
    <Layout title="Ops" subtitle="Que pena" onBack={onMenuBack}>
      <div className="error">
        <Empty description="Acesso negado ou servidor indisponível!" />
      </div>
    </Layout>
  );
};

export default Error;
