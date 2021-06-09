# k6 vs JMeter vs Gatling

PoC para comparar el trabajo que realiza k6, JMeter y Gatling en una prueba al [API de Star Wars](https://swapi.dev).

## k6

[k6](https://k6.io) es un SaaS que permite realizar pruebas de carga. Es muy liviano y fácil de utilizar.

Esta PoC está hecha usando JavaScript.

### Uso

Ejecutar:

```bash
cd k6
k6 run star-wars.js
```

## JMeter

[JMeter](https://jmeter.apache.org) es una herramienta que permite realizar pruebas de carga. Tiene un cliente que se ejecuta localmente y una interfaz gráfica donde es posible agregar las pruebas HTTP y sus validaciones.

### Uso

Descargar [JMeter](https://jmeter.apache.org/download_jmeter.cgi) y correr el script [Star Wars test plan.jmx](https://github.com/juanalvarez123/k6-jmeter-gatling-poc/blob/master/jmeter/Star%20Wars%20test%20plan.jmx).

### Componentes de JMeter

* HTTP Request: Obtiene la información de un planeta.
* Response Assertion: Valida textualmente que el nombre del planeta (junto con el atributo) venga en la respuesta.
* JSON Assertion: Valida por estructura que un atributo venga en la respuesta y tenga un valor.
* View results tree: Resultados de las peticiones HTTP y sus validaciones.
* Assertion Results: Resultados de las validaciones. Si no hay errores muestra un reporte vacío.

## Gatling


