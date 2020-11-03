import React from 'react';
import * as PropTypes from 'prop-types';
import {Tag} from 'antd';

import './tag-status.css';

const TagStatus = ({ status }) => (
  <div className="tag-status">
    <Tag color={status.chave === 'PENDENTE' ? 'red' : 'blue'}>
      {status.descricao}
    </Tag>
  </div>
);

TagStatus.propTypes = {
  status: PropTypes.object.isRequired,
};

export default TagStatus;
