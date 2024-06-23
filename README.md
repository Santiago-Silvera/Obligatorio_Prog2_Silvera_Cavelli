# Obligatorio Programacion 2

### Autores: Santiago Silvera, Juan Ignacio Cavelli

## Instrucciones de uso:

La clase principal del trabajo es Sistema. \\ 
Esta clase tiene varias funciones. La principal (main) es la entrada al programa y se encarga de ejecutar el cargado del CSV. Es importante colocar correctamente el path del CSV.
Lo siguiente que hará será iniciar el menú y mantenerlo en bucle hasta que se cierre con la opción 6. \\
Un resumen de las 5 consultas se muestra en la pantalla y tras seleccionar una se consultan los datos necesarios para la consulta. 

Al momento de ingresar fechas se debe seguir el formato YYYY-MM-DD (e.g. 2024-05-11) y al momento de colocar el pais se debe tener en mente la convención que utiliza Spotify, aunque las mayúsculas no son necesarias. Al momento de colocar el nombre del artista tampoco es necesario que se tengan en cuenta las mayúsculas.
Si se desea saber informacion sobre el top global se puede colocar "GLOBAL" (es case insensitive).

## Diagrama UML

![Diagrama UML]{"../../"}

## Descripcion de los metodos principales

Para inicializar la carga del CSV lo primero que se hace es inicializar una instancia de la clase CSVLoader. Se tomo esta decision en lugar de hacer el metodo de carga estatico para lograr un paralelismo con el funcionamiento de la clase Scanner, donde se debe inicializar una instancia del scannner.
Luego, se debe llamar al metodo LoadCSV (se ignora el camelCase por claridad de la letra "L") que requiere un String que debe ser la direccion del archivo CSV (path). Si no se encuentra el archivo el programa arroja un error e informa al usuario. 

Luego de la carga de datos, que se hace a dos estructuras principales, topSongsByDateCountry y topArtistByAppearance (mas sobre ellas luego), se entra en un bucle que muestra el menu con las opciones posibles.
Dentro del menu se tienen 6 opciones que son las 5 consultas de la letra propuesta y una opcion para cerrar el programa.

### Las consultas

#### Consulta 1 

Al usuario se le pide una fecha en formato YYYY-MM-DD y un pais (case insensitive) y se accede al hash top topSongsByDateCountry. Este hash tiene como clave una fecha que corresponde con la fecha de una cancion y como valor tiene otro hash. El hash interno tiene como clave un String que corresponde con el pais de una cancion y como valor tiene una LinkedList de canciones que cumplen con los requisitos de fecha y pais (que tienen la fecha y pais igual a la clave del hash interno y externo).
De esta manera, se accede a la lista de canciones en tiempo constante pero para devolver la cantidad correcta de canciones se itera sobre la LinkedList para devolver la cantidad correcta.

#### Consulta 2 

Objetivo de la consulta: Determinar las 5 canciones más populares que aparecen en el mayor número de listas de "Top 50" en diferentes países para un día dado. Las canciones deben estar ordenadas de manera descendente según su frecuencia de aparición.

Paso a Paso:

Para inicializar los resultados, se crea una lista vacía top5 de tipo MyList<Song> para almacenar las 5 canciones más populares.

Para verificar la existencia de la fecha, se verifica si la fecha dada (fechaDada) existe en el hash topSongsByDateCountry, que almacena las canciones del top 50 por país y por fecha. Si no hay datos para esa fecha, se retorna una lista vacía.

Para contar las apariciones de las canciones, se inicializa un hash songCount de tipo MyHash<String, Integer> para contar el número de veces que cada canción aparece en las listas de top 50 de diferentes países.
Se recorren todas las listas de top 50 para cada país en la fecha dada, de manera que para cada país se obtiene la lista de canciones.
Luego se itera sobre cada canción en la lista y se obtiene su ID de Spotify (songId).
Si la canción ya existe en songCount, se incrementa su contador. Si no, se agrega al hash con un contador inicial de 1.

Luego, para determinar el top 5, se utiliza un heap máximo (MyHeap<SongCountPair>) para almacenar los pares de canción y conteo (SongCountPair), limitando su tamaño a 5 elementos. El heap es máximo ya que necesitamos obtener las canciones en orden descendente (según las apariciones).
Se itera sobre las entradas del hash songCount; para cada canción, se obtiene el conteo de apariciones.
Se crea un objeto SongCountPair que contiene la instancia de la canción y su conteo de apariciones.
Si el heap tiene menos de 5 elementos, se inserta directamente el par. Si el heap ya tiene 5 elementos, se compara el nuevo par con el par de menor conteo en el heap (la cima del heap) y, si el nuevo par tiene un conteo mayor, se reemplaza el elemento de menor conteo en el heap.

Luego, se extraen las canciones del heap, las cuales estarán en orden descendente de popularidad, y se añaden a la lista top5.
Dicha lista se retorna como resultado, conteniendo las 5 canciones más populares ordenadas de manera descendente.

Método Auxiliar getExampleSongFromId: Este método se utiliza para obtener una instancia de Song dado un ID de Spotify (songId) para una fecha específica.
Se recorre el hash countryToSongs para la fecha dada, iterando sobre cada país y su lista de canciones. Si se encuentra una canción con el ID especificado, se retorna esa instancia de Song.

Clase interna SongCountPair: Esta clase se utiliza para manejar pares de canción y conteo de apariciones. Implementa la interfaz Comparable<SongCountPair> para permitir la comparación basada en el conteo de apariciones.
El método compareTo compara los pares por el conteo de apariciones, facilitando el manejo de las canciones más populares en el heap.

#### Consulta 3 

Primero se le solicita al usuario que ingrese una fecha inicial y una fecha final para poder determinar el rango de fechas. Luego de esto, se itera sobre el hash topArtistByAppearance que contiene como clave una fecha y como valor un hash interno que tiene como clave un String (nombre del artista) y como valor la cantidad de apariciones que tiene en esa fecha.
A medida que se recorre el hash a lo largo del rango de fechas se llena otro hash que lleva constancia de las apariciones totales de los artistas en estas fechas.
Por ultimo, se vuelve a iterar sobre el hash creado durante la consulta, agregando en orden los artistas con mas apariciones a una LinkedList que es devolvida.

#### Consulta 4 

Para esta consulta se pide al usuario el nombre del artista, el top 50 (que sera el pais del top) y una fecha. Con los datos de la fecha y el pais, se accede a topSongsByDateCountry y se extraen todas las canciones para esa fecha en ese pais. 
Luego de esto, se itera sobre el LinkedList de canciones y se mira si el artista es parte de los artistas de la cancion, si lo es, se aumenta un contador.
Al final,se devuelve el contador.

#### Consulta 5 

Primero se solicita al usuario un rango de fechas y un rango de tempos. Luego de esto, se itera sobre el rango de fechas en topSongsByDateCountry, donde para cada fecha se miran todos los paises y para cada pais se miran todas las canciones.
Para cada cancion se verifica que su tempo este en el rango de tempos y si es asi se agrega esta cancion a un hash. 
Por utimo, se devuelve el tamaño del hash.
