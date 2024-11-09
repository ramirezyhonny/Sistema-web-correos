import React from "react";
import { Box } from "@chakra-ui/react";
import Header from "../Header/header";
import { Outlet } from "@tanstack/react-router";

const GlobalLayout = () => {
    return (
        <Box display="flex" flexDirection="row" minHeight="100vh">
            <Header/>
            <Box padding="0" margin="0" boxSizing="border-box">
                <Outlet />
            </Box>
        </Box>
    )
}
export default GlobalLayout;