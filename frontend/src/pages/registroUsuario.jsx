import React, { useState } from "react";

const RegistroUsuario = () => {
    const [nombre, setNombre] = useState('');
    const [email, setEmail] = useState('');
    const [dni, setDni] = useState('');
    const [isAdmin, setIsAdmin] = useState(false);
    const [mensaje, setMensaje] = useState('');

    const handleRegistro = async (e) => {
        e.preventDefault(); // evita el refresco de página

        try {
            const response = await fetch('http://localhost:8080/WebCorreos/usuarios/registro', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nombre, email, dni, isAdmin })
            });

            if (!response.ok) {
                throw new Error('Error: ' + response.statusText);
            }

            const data = await response.json();
            setMensaje("Usuario registrado correctamente");
            console.log(data);
        } catch (error) {
            console.error("Error en el registro:", error);
            setMensaje("Error en el registro");
        }
    };

    return (
        <div>
            <h2>Registro de Usuario</h2>
            <form onSubmit={handleRegistro}>
                <div>
                    <label>Nombre:</label>
                    <input type="text" value={nombre} onChange={(e) => setNombre(e.target.value)} required/>
                </div>
                <div>
                    <label>Email:</label>
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
                </div>
                <div>
                    <label>Dni:</label>
                    <input type="text" value={dni} onChange={(e) => setDni(e.target.value)} required/>
                </div>
                <div>
                    <label>Admin:</label>
                    <input
                        type="checkbox"
                        checked={isAdmin}
                        onChange={(e) => setIsAdmin(e.target.checked)}
                    />
                </div>
                <button type="submit">Registrar Usuario</button>
            </form>
            {mensaje && <p>{mensaje}</p>}
        </div>
    );
};

export default RegistroUsuario;