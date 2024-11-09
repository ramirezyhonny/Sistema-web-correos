
import { createRootRoute, Link, Outlet } from "@tanstack/react-router";
import { TanStackRouterDevtools } from "@tanstack/router-devtools";
import GlobalLayout from "../components/Global/globalLayout";


export const Route = createRootRoute({
    component: () => (
        <GlobalLayout>
            <div>
                <Link to="/">Home</Link>{' '}
                <Link to="/rutaRegistroUsuario">Registro</Link>
                <Link to="/rutaListaCorreos">Correos</Link> 
                <Link to="/rutaListaUsuarios">Usuarios</Link>
                <Link to="/rutaRedactarCorreos">Redactar</Link>
                <Link to="/rutaBuscarCorreos">Buscar correos</Link>
            </div>
            <TanStackRouterDevtools />
        </GlobalLayout>

        
    ),
})
