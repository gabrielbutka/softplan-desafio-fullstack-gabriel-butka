import {Tag} from 'antd';
import * as PropTypes from 'prop-types';
import {Utils} from '../../utils';

import './tag-sim-nao.css';

const TagSimNao = ({ value }) => (
  <div className="tag-sim-nao">
    <Tag color={value ? 'blue' : 'red'}>{Utils.boolToStr(value)}</Tag>
  </div>
);

TagSimNao.propTypes = {
  value: PropTypes.bool.isRequired,
};

export default TagSimNao;
