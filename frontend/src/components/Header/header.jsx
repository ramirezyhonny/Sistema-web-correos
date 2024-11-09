import React from "react";
import { Box, Flex, Spacer, useColorMode, IconButton, Stack } from "@chakra-ui/react";
import { SunIcon, MoonIcon } from "@chakra-ui/icons";
import { Link } from "@tanstack/react-router";
import styles from "./header.module.scss";
const Header = React.memo(() => {
    const {colorMode, toggleColorMode } = useColorMode();

    return(
        <Box color="white" padding="5" w="15%" h="100vh" bg="#47bb94" display="flex" >
            <Flex align="center" display="flex" flexDir="column" border="1px solid red" width="100%" justifyContent="center">
                <Stack  className={styles.stack_header}>
                    <Link to="/" className={styles.stack_link}>Home</Link>
                    <Link to="/rutaRegistroUsuario" className={styles.stack_link}>Registro</Link>
                    <Link to="/rutaListaCorreos" className={styles.stack_link}>Correos</Link>
                    <Link to="/rutaListaUsuarios" className={styles.stack_link}>Usuarios</Link>
                    <Link to="/rutaRedactarCorreo" className={styles.stack_link}>Redactar</Link>
                    <Link to="/rutaBuscarCorreos" className={styles.stack_link}>Buscar correos</Link>
                </Stack>
                <IconButton
                    aria-label={`Switch to ${colorMode === 'light' ? 'dark' : 'light'} mode`}
                    icon={colorMode === 'light' ? <MoonIcon /> : <SunIcon />}
                    onClick={toggleColorMode}
                    ml="4"
                />
                
            </Flex>
        </Box>
    );
});
export default Header;