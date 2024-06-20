# Obligatorio Programacion 2

### Autores: Santiago Silvera, Juan Ignacio Cavelli

En esta rama intento de hacer una interfaz grafica mediante un servidor de Node js. Esto no es una app fullstack pero es lo mas similar que se puede hacer sin pasarse demasiado de los requerimientos de la propuesta. Se cambia el proceso de "compilado" del proyecto del integrado en IntelliJ a Maven, esto para poder agregar algunas dependencias como Spring Boot de manera mas sencilla.

Aqui creamos un API endpoint sencillo con la ayuda de Spring Boot. Luego llamamos a ese endpoint mediante una sencillo servidor de express y axios ejecutado con node.

Consideraciones que tendremos que tener:

- Es comun enviar los datos del backend al frontend en forma de JSON. Traducir un arbol en JSON es una tarea relativamente sencilla por lo que hay que considerar utilizar esa estructura en algun momento del proceso de parsing.
- Como en el archivo csv se tiene el id de la cancion, eso se puede usar como clave para un hash table. Luego hay que pensar en como acceder a los datos. Tambien se puede crear otra tabla que tenga los datos hasheados por pais, fecha, etc.
- La logica recien aplicada a las canciones tambien puede aplicar a los artistas.
- Un simulador de un reporduccion puede utilizar un circular linked list y una queue para anotar las canciones. Obviamente, por derechos de autor no podriamos reproducir las canciones.
