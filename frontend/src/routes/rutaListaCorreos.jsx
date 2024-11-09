import { createFileRoute } from '@tanstack/react-router';
import ListaCorreos from '../pages/listaCorreos';

export const Route = createFileRoute('/rutaListaCorreos')({
  component: ListaCorreos,
});
