import { createFileRoute } from '@tanstack/react-router'
import RedactarCorreo from '../pages/redactarCorreo'

export const Route = createFileRoute('/rutaRedactarCorreo')({
  component: RedactarCorreo,
})
