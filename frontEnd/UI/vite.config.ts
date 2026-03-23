import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      '/karyawarna': {
        target: 'http://localhost:8100', // user-services
        changeOrigin: true,
      },
    },
  },
});