import { notification } from 'antd';

const openNotification = (type, message) => {
    notification[type]({
        message
    });
};

export const successNotification = (message) =>
    openNotification('success', message);

export const infoNotification = (message) =>
    openNotification('info', message);

export const warningNotification = (message) =>
    openNotification('warning', message);

export const errorNotification = (message) =>
    openNotification('error', message);
