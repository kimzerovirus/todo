let backhendHost;

const hostname = window && window.location && window.location.hostname;

if (hostname === 'localhost') backhendHost = 'http://localhost:8080';

export const API_BASE_URL = backhendHost;
