
# ApiData
Api prueba Enfocada en las consultas
## Descripcion

Se enfoca en la generacion de Excels De consultas de datos de Usuaio,Items,ordenes

## Getting Started

### Dependencias

* Es necesario el uso de Docker para poder consumir las imagenes docker de Mongo y de RabbitMQ para el correcto funcionamiento del microservicio

### Instalacion

* How/where to Get ,Git , Java 17 , Intelij Community Edition and Maven

### Ejecutar el programa

* How to run the program
```
By MVN 
or 
InteliJ Cumunitty
```


### Operaciones

* Reporte ClientePedidos 
    * Excel
      * Ruta : 
```
Salida de campos reporte ClientePedidos:
  Datos del cliente
    ● Nombre
    ● Apellido paterno
    ● Apellido materno
    ● Correo electrónico
  Datos de entrega
    ● Dirección de envío
  Datos del pedido
    ● Código del producto (itemId)
    ● Cantidad
    ● Canal de venta
    ● Estatus de pedido (Fecha estimada de entrega)
   
```
* Reporte Pedidos Por orderRef, orderStatus,storeName
    * Excel
        * Ruta :
```
Salida de campos reporte PedidosBy orderRef, orderStatus,storeName :
  Datos del pedido
    ● Orden
    ● Código del producto (itemId)
    ● Cantidad
    ● Canal de venta
    ● Estatus de pedido (Fecha estimada de entrega)
   
```

* Reporte Items Por displayName
    * Excel
        * Ruta :
```
Salida de campos reporte Items por displayName :
  Datos del pedido
    ● Código del producto (itemId)
    ● Cantidad
    ● sku
    ● Producto Nombre (displayName)
    ● Status del producto(deliveryStatus) 
   
```

## Authors

Contributors names and contact info

ex. Sergio Granados
ex. [sergio.daniel.granados@hotmail.com](sergio.daniel.granados@hotmail.com)

## Version History

* 0.1
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Conocimientos

Recursos Externos
* [Items](https://6994a4eab081bc23e9c0f61e.mockapi.io/api/v1/items)
* [Pedidos](https://6994a4eab081bc23e9c0f61e.mockapi.io/api/v1/pedidos)