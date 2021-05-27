# [Fines Académicos] Servicio Operador Entidad Pública

### ¿Cómo probar?

* Subir el mockup del Servicio Operador Cliente (Ciudadano)
```shell script
$ mvn clean package
$ mvn spring-boot:run
```

* Ejecutar el llamado POST con los siguientes parámetros.

Parámetro | Valor
---|---
Metodo HTTP|POST
Endpoint|http://localhost:8083/operador/archivos/123456
Request Param| archivos
Request Headers|Content-Type=multipart/form-data