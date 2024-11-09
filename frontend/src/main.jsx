import React, { StrictMode } from 'react'
import ReactDOM from 'react-dom/client'
import { RouterProvider, createRouter } from '@tanstack/react-router'
import {routeTree} from './routeTree.gen'
import { ChakraProvider, ColorModeScript } from '@chakra-ui/react'

import theme from './theme'


const router = createRouter({ routeTree })

const rootElement = document.getElementById('root');
if (rootElement && !rootElement.innerHTML){
    const root = ReactDOM.createRoot(rootElement)
    root.render(
        <StrictMode>
          <ChakraProvider theme={theme}>
            <ColorModeScript initialColorMode={theme.config.initialColorMode} />
            <RouterProvider router={router}/>
          </ChakraProvider>
        </StrictMode>,
    );
}