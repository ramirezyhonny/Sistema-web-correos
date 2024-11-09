
import React, { useEffect, useState } from "react";


const ListaUsuarios = () => {
    const [usuarios, setUsuarios] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const obtenerUsuarios = async () => {
            try {
                const response = await fetch("http://localhost:8080/WebCorreos/usuarios/obtener");

                if (!response.ok) {
                    throw new Error(`Error: ${response.statusText}`);
                }

                const data = await response.json();
                setUsuarios(data);
            } catch(err) {
                setError("Hubo un error al obtener la lista de usuaris");
                console.log("Error", err);
            }
        };
        obtenerUsuarios();
    }, []);

    const handleEliminar = async (email) => {
        try {
            const response = await fetch(`http://localhost:8080/WebCorreos/usuarios/eliminar?email=${email}`, {
                method: 'DELETE'
            });

            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }
            setUsuarios(usuarios.filter(usuario => usuario.email !== email));
        } catch (error) {
            console.error("Error al eliminar usuario:", error);
            setError("Hubo un error al eliminar el usuario");
        }
    }
    return (
        <div>
            <h2>Lista de usuarios</h2>
            {error && <p>{error}</p>}

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>E-mail</th>
                        <th>DNI</th>
                        <th>Admin</th>
                    </tr>
                </thead>
                <tbody>
                    {usuarios.map((usuario) => (
                        <tr key={usuario.id}>
                            <td>{usuario.id}</td>
                            <td>{usuario.nombre}</td>
                            <td>{usuario.email}</td>
                            <td>{usuario.dni}</td>
                            <td>{usuario.isAdmin ? "Si" : "No"}</td>
                            <td>
                                <button onClick={()=> handleEliminar(usuario.email)}>eliminar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListaUsuarios