# Definición de Exchanges, Queues y Mensajes

## Exchange

- **Nombre:** pagos.estatus.cambiado
- **Tipo:** topic
- **Durable:** true

## Queue(s)

| Queue                | Binding Key         | Descripción                                  |
|----------------------|---------------------|----------------------------------------------|
| pagos.notificaciones | estatus.cambiado.*  | Procesa notificaciones de cambios de estatus |
| pagos.verificacion   | estatus.cambiado.*  | Inicia procesos de verificación externa      |

## Formato del mensaje

```json

{
  "idTransaccion": "abc123",
  "nuevoEstatus": "aprobado",
  "fechaCambio": "2025-04-22T12:00:00Z"
}

```
* Los valores para nuevoEstatus posibles: PENDIENTE, APROBADO, RECHAZADO
