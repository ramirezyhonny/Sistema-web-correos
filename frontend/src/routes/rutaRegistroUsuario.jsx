import { createFileRoute } from '@tanstack/react-router'
import RegistroUsuario from '../pages/registroUsuario'

export const Route = createFileRoute('/rutaRegistroUsuario')({
  component: RegistroUsuario,
})
