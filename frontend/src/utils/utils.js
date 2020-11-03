import {i18n} from '@lingui/core';
import {t} from '@lingui/macro';

const dataFilter = (filter, data) => {
  if (!Array.isArray(data) || !filter) {
    return data;
  }

  let newData = [];

  data.forEach(item => {
    let selected = false;

    Object.keys(item).forEach(key => {
      if (!selected && typeof item[key] === 'string') {
        const itemValue = item[key].toLowerCase();
        if (itemValue.includes(filter.toLowerCase())) {
          selected = true;
        }
      }
    });

    if (selected) {
      newData = newData.concat(item);
    }
  });

  return newData;
};

const dataReplace = (item, data) => {
  if (!Array.isArray(data) || !Boolean(item)) {
    return data;
  }

  let newData = [];

  data.forEach(itemData => {
    if (item.id !== itemData.id) {
      newData = newData.concat(itemData);
    } else {
      newData = newData.concat(item);
    }
  });

  return newData;
};

const dataDelete = (item, data) => {
  if (!Array.isArray(data) || !Boolean(item)) {
    return data;
  }
  return data.filter(({ id }) => id !== item.id);
};

const boolToStr = bool => {
  return bool ? i18n._(t`Sim`) : i18n._(t`Não`);
};

const enumFind = (key, values) => {
  return values.find(({ chave }) => chave === key);
};

const enumDescription = (key, values) => {
  const found = enumFind(key, values);
  return Boolean(found) ? found.descricao : '';
};

const enumsToFilter = values => {
  return values.map(({ chave, descricao }) => ({
    text: descricao,
    value: chave,
  }));
};

export function formatRoutePath({ path }, params) {
  let newPath = path;
  const p = params;

  do {
    const param = p.shift() || '';
    newPath = newPath.replace(new RegExp(':[^/]*'), param);
  } while (p.length);

  return newPath;
}

const utils = {
  dataFilter,
  dataReplace,
  dataDelete,
  boolToStr,
  enumFind,
  enumDescription,
  enumsToFilter,
  formatRoutePath,
};

export default utils;
