[<<< Volver](/docs/README.md)
# Wallet de Autonomous Oracle Data Base
## ¿Para que la necesita?
La wallet la necesita para autenticarse en la base de datos de oracle, sin esta no podrás acceder a la base de datos

## ¿En que ruta debe estar la wallet?
> [!IMPORTANT]  
> La wallet debe estar descomprimida dentro de las rutas especificadas a continuacion

En el caso de usar Windows la wallet debe estar en:
```
    C:/oracle/ecommerce
```

En el caso de usar un entorno Linux:
```
    /opt/oracle/ecommerce
```

## ¿Cómo el programa sabe que ruta usar?
El proyecto cuenta con un paquete de configuración en el que tiene un archivo llamado `DynamicTNSConfig.java`. Este archivo se encarga de detectar sí se encuentra corriendo en un entorno Windows o Linux y dependiendo de aquello elije una ruta u otra. Este proceso se ejecuta al momento de iniciar la aplicación por lo que hace que no sea necesario ninguna configuración adicional.
