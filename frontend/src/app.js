import React from 'react';
import {Route, Switch} from 'react-router-dom';
import {Routes} from './constants';
import {Error} from './screens';
import {AuthService} from './services';

import 'antd/dist/antd.css';

const App = () => (
  <Switch>
    {Object.keys(Routes).map(key => {
      const { path, roles, component: Component } = Routes[key];
      return (
        <Route
          exact={true}
          path={path}
          render={props => {
            if (roles.length === 0 || AuthService.hasRole(roles)) {
              return <Component {...props} />;
            }
            return <Error {...props} />;
          }}
        />
      );
    })}
    <Route exact component={Error} />
  </Switch>
);

export default App;
