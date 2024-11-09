import { createFileRoute } from '@tanstack/react-router'
import ListaUsuarios from '../pages/listaUsuarios'

export const Route = createFileRoute('/rutaListaUsuarios')({
  component: ListaUsuarios,
})
