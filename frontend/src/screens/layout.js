import React from 'react';
import {Avatar, Layout as AntLayout, Menu, PageHeader, Space, Typography,} from 'antd';
import {LogoutOutlined, UserOutlined} from '@ant-design/icons';
import {Trans} from '@lingui/macro';
import {Menus} from '../constants';
import {Link} from 'react-router-dom';
import {AuthService} from '../services';

import './layout.css';

const Layout = ({ title, subtitle, menuKey, onBack, children }) => (
  <div className="layout">
    <AntLayout>
      <AntLayout.Sider breakpoint="lg" collapsedWidth="0">
        <div className="app-logo">
          <img src="/logo-negativa.svg" alt="Softplan" />
        </div>
        <Menu theme="dark" defaultSelectedKeys={menuKey}>
          {Object.keys(Menus).map(menuKey => {
            const { key, title, icon, route } = Menus[menuKey];
            const disabled = !AuthService.hasRole(route.roles);
            return (
              <Menu.Item key={key} icon={icon} disabled={disabled}>
                <Link to={route.path}>{title}</Link>
              </Menu.Item>
            );
          })}
          <Menu.Divider />
          <Menu.Item
            key="logout"
            onClick={() => AuthService.logout()}
            icon={<LogoutOutlined />}>
            <Trans>Logout</Trans>
          </Menu.Item>
        </Menu>
      </AntLayout.Sider>
      <AntLayout>
        <AntLayout.Header>
          <PageHeader
            onBack={onBack}
            title={title.toUpperCase()}
            subTitle={subtitle}
            extra={[
              <Space>
                <Typography.Text type="secondary">
                  {AuthService.getUserName()}
                </Typography.Text>
                <Avatar size="small" icon={<UserOutlined />} />
              </Space>,
            ]}
          />
        </AntLayout.Header>
        <AntLayout.Content>
          <div className="app-content">{children}</div>
        </AntLayout.Content>
        <AntLayout.Footer>
          <Trans>
            MVP Gerenciador de Processos • Softplan • 2020 • Gabriel Butka
          </Trans>
        </AntLayout.Footer>
      </AntLayout>
    </AntLayout>
  </div>
);

export default Layout;
