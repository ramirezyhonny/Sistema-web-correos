import React, { useState } from "react";

const RedactarCorreo = () => {
    const [remitenteEmail, setRemitenteEmail] = useState('');
    const [destinatarioEmail, setDestinatarioEmail] = useState('');
    const [asunto, setAsunto] = useState('');
    const [mensajeEmail, setMensajeEmail] = useState('');
    const [mensaje, setMensaje] = useState('');

    const handleRedactarCorreo = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/WebCorreos/correos/registro', {
                method: 'POST',
                headers: {
                    'Content-Type':'application/json'
                }, 
                body: JSON.stringify({
                    remitente_email: remitenteEmail,
                    destinatario_email: destinatarioEmail, 
                    asunto,
                    mensaje: mensajeEmail 
                })
            });
            if (!response.ok) {
                throw new Error('Error: ' + response.statusText);
            }
            const data = await response.json();
            setMensaje("Correo registrado correctamente");
            console.log(data);
        } catch (error) {
            console.error("Error en el registro: ", error);
            setMensaje("Error en el registro")
        }
    }
    return (
        <div>
            <h2>Redactar correo</h2>
            <form onSubmit={handleRedactarCorreo}>
                <div>
                    <label htmlFor="">De:</label>
                    <input type="email" value = {remitenteEmail} onChange={(e) => setRemitenteEmail(e.target.value)} placeholder="email del remitente" required />

                </div>
                <div>
                    <label htmlFor="">Para:</label>
                    <input type="email" value={destinatarioEmail} onChange={(e) => setDestinatarioEmail(e.target.value)} placeholder="email del destinatario" required />
                </div>
                <div>
                    <label htmlFor="">Asunto:</label>
                    <input type="text" value={asunto} onChange={(e) => setAsunto(e.target.value)}  required />
                </div>
                <div>
                    <label htmlFor="">Mensaje:</label>
                    <input type="text" value={mensajeEmail} onChange={(e) => setMensajeEmail(e.target.value)} required />
                    
                </div>
                <button type="submit">Enviar</button>
            </form>
            {mensaje && <p>{mensaje}</p>}
        </div>
    );
};

export default RedactarCorreo;