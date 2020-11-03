import {Spin} from 'antd';

import './loading.css';

const Loading = () => (
  <div className="loading">
    <Spin spinning={true} size="large" />
  </div>
);

export default Loading;
