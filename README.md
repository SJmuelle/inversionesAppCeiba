# InversionesApp (Spring Boot + MongoDB) - No Lombok + Reportes

## Endpoints
- **POST** `/api/clientes`  (incluye `cedula`)
- **GET** `/api/clientes`
- **GET** `/api/clientes/{id}`

- **POST** `/api/fondos`  (nombre, montoMinimo, categoria: FPV|FIC)
- **GET** `/api/fondos`   (semillado inicial de 5 fondos)

- **POST** `/api/suscripciones`
- **POST** `/api/cancelaciones`
- **GET** `/api/transacciones/{clienteId}?limit=10`

- **GET** `/api/reportes/fondos-por-cliente?cedula=XXXXXXXX`
  - Devuelve todos los fondos con `suscrito` y `montoVinculado` (aperturas - cancelaciones) para esa cédula.

## Reglas
- Saldo inicial COP 500.000 por cliente.
- Mínimo por fondo al suscribirse.
- Cada suscripción **descuenta** el saldo del cliente.
- Al cancelar, se **devuelve** el neto vinculado de ese fondo.
- Cada transacción tiene UUID.

## Ejecutar
```bash
mvn spring-boot:run
```
