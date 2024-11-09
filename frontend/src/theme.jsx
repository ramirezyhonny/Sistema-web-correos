import { extendTheme } from '@chakra-ui/react';

const theme = extendTheme({
  config: {
    initialColorMode: 'light',
    useSystemColorMode: false,
  },
  styles: {
    global: {
      body: {
        transition: 'background-color 0.9s ease, color 0.9s ease',
      },
    },
  },
});

export default theme;