import React, { useEffect, useState } from "react";

const ListaCorreos = () => {
    const [correos, setCorreos] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const obtenerCorreos = async () => {
            try {
                const response = await fetch("http://localhost:8080/WebCorreos/usuarios/obtener");

                if (!response.ok) {
                    throw new Error(`Error: ${response.statusText}`);
                }

                const data = await response.json();
                setCorreos(data);
            } catch (err) {
                setError("Hubo un error al obtener la lista de correos");
                console.error("Error:", err);
            }
        };

        obtenerCorreos();
    }, []);

    return (
        <div>
            <h2>Lista de Correos</h2>
            {error && <p>{error}</p>}
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Remitente</th>
                        <th>Destinatario</th>
                        <th>Asunto</th>
                        <th>Mensaje</th>
                        <th>Fecha de Envío</th>
                        <th>Estado</th>
                        <th>Leído</th>
                    </tr>
                </thead>
                <tbody>
                    {correos.map((correo) => (
                        <tr key={correo.id}>
                            <td>{correo.id}</td>
                            <td>{correo.remitente_id}</td>
                            <td>{correo.destinatario_id}</td>
                            <td>{correo.asunto}</td>
                            <td>{correo.mensaje}</td>
                            <td>{correo.fecha_envio}</td>
                            <td>{correo.estado}</td>
                            <td>{correo.leido ? "Sí" : "No"}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListaCorreos;