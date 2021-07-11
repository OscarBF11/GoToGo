<a href="github.com/GoToGo">
    <img src="https://user-images.githubusercontent.com/51002396/125190940-1b601c80-e240-11eb-81b0-a60eff1acddd.png" alt="GoToGo logo" title="GoToGo APP" align="right" height="60" />
</a>

# GoToGo

![License](https://user-images.githubusercontent.com/51002396/125190891-cae8bf00-e23f-11eb-8724-270c8925f2a6.png)

:star: Star us on GitHub — it motivates us a lot!

GoToGo is an Android application capable of recommending and creating routes on a map in real time.


![GoToGo emergency](https://user-images.githubusercontent.com/51002396/125190013-57dd4980-e23b-11eb-84d5-711cfe32be54.jpg)
![GoToGo contact](https://user-images.githubusercontent.com/51002396/125190018-5ca1fd80-e23b-11eb-8c77-a53dc39be6de.jpg)
![GoToGo categories](https://user-images.githubusercontent.com/51002396/125190020-5f9cee00-e23b-11eb-817e-ff15ce535b7d.jpg)
![GoToGo route](https://user-images.githubusercontent.com/51002396/125190021-60358480-e23b-11eb-8248-1f0ecfb753d7.jpg)

## Table of content

- [Instalación](#INSTALL)
- [Funciones](#FUNCIONS)
- [Versiones](#VERSIONES)
    - [Versión 0](#version-0)
    - [Versión 1](#version-1)
    - [Versión 2](#version-2)
    - [Versión 3](#version-3)
- [License](#licencia)

## INSTALL

This document is for Android Studio **3.6 release and later**.

- Stable release: 3.0 (Android 7.0 and later)

## FUNCIONS
GoToGo es una aplicación con la que el usuario puede descubrir nuevos sitios por toda Barcelona
dependiendo de las categorías que el usuario escoja. Para ello el programa tiene integrado las
siguientes funcionalidades que se mostrarán sobre el mapa: el usuario podrá escoger entre una o
más categorías con las que se generará una ruta ordenando los lugares según la proximidad del
usuario. Las categorías disponibles con las que se pueden generar son las siguientes:

### Cultura cinematografica
Esta categoría incluye todo lo relacionado con el tema de la cinematografía, desde distintos cines
hasta museos relacionados con el mismo

### Música
Aquí nos centramos en todos los lugares relacionados con la música. Entre estos encontramos
lugares donde escuchar conciertos hasta museos y conservatorios

### Arquitectura
En esta podemos encontrar grandes obras arquitectónicas de distintos autores que se pueden
encontrar a lo largo y ancho de todo Barcelona.

### Gastronomía
Esta categoría está dedicada en recopilar distintos restaurantes, bares donde degustar la comida
típica de la zona, la mediterránea.

### Monumentos históricos
Esta categoría recoge todos aquellos lugares que, ya sea por su calidad o interés histórico, artístico o
por su antigüedad, se han declarado monumento histórico.

### Pintura
En pintura podemos encontrar gran variedad de galerías que recogen en su conjunto, una gran
variedad de obras pictóricas.

### Arte urbano
En arte urbano, encontramos una serie de obras artísticas que se encuentran por las calles de
Barcelona, desde autores conocidos a autores anónimos.

### Deportes
Deportes recoge todos aquellos lugares relacionados con el mismo de todo tipo. Algunos ejemplos
son estadios y tiendas especializadas.

### Ocio
En ocio incluimos todos esos lugares donde, solo o en compañía, puedes disfrutar de diversas
actividades lúdicas.

### Poser
Esta categoría recoge todos aquellos lugares preciosos por Barcelona donde hacer las mejores
fotografías.

### Zonas conflictivas
Una vez generada la ruta, sobre el mapa se marcan una serie de zonas conflictivas a modo de
círculos rojos. Estas son zonas que, después de un estudio, han sido catalogadas como “no seguras”.
Si el usuario entra en una de estas zonas, se le enviará una notificación advirtiéndole.

### Emergencias
En este apartado se le ofrece al usuario un acceso rápido a los contactos de emergencia de
Barcelona (Bomberos, Servicios sociales, Policía y Emergencias sanitarias) donde al pulsar sobre uno,
realiza directamente una llamada. También permite al usuario añadir dos contactos adicionales.
Otro aspecto que cubre es mostrar al usuario si está, o no, en una zona segura.

### Contacto
Esta función permite que el usuario se ponga en contacto de forma directa con nosotros.
Dependiendo de cuál sea su finalidad (las opciones disponibles para el usuario son soporte o
sugerencias) se enviará a un correo electrónico u otro, ya sea con fines para mejorar la aplicación,
sugerir lugares o solucionar posibles bugs.




## VERSIONES
A lo largo del proyecto ha habido inmensidad de versiones y a continuación ponemos las más relevantes:

### Version 0
Estructuramos el formato de la aplicación (Colocación del menú) y añadimos paleta de
colores.
- Se decidió desde que base íbamos a partir, si íbamos a
utilizar plantilla de Android Studio y si estos tuvieran algún
tipo de efectos “scroll”.
- Planteamos la plantilla base para la aplicación, en esta se
decide una paleta de colores orientativa y NO final ya que
el diseño planteado en los bocetos puede variar mucho a
la realidad.
- Por último, se propone un estándar o librería de iconos
para la aplicación.

### Version 1
Primeras implementaciones de los menús principales.
- Se implementa en su totalidad el menú principal de
Categorías, por primera vez este es completamente
estable (Anteriormente había tenido muchos problemas
hasta llegar a una versión estable).
- Se implementa un contacto sencillo y funcional.
- Se hacen grandes avances en la gestión del mapa, pero
aún no está listo.

### Version 2
Se hacen grandes cambios visuales y se modifican drásticamente menús cómo
Contacto o Emergencias.
- Se modifica la paleta de colores original y se
implementas nuevos iconos tanto de botones cómo de
categorías.
- Se borra y se vuelve a plantear el diseño de Emergencias
desde un punto de vista de usuario, mejorando la
comprensión del menú, el uso y abarcando nuevas
funciones.
- Se le hace un lavado de cara a Categorías mejorando su
atractivo y facilidad de uso.
- Se implementa por primera vez un mapa funcional con
pocos BUG’s.
- Se mejora el código de algunos menús para obtener
mayor velocidad en dispositivos de pocos recursos.
Además, se solucionan el 80% de los problemas o Bug’s
conocidos.

### Version 3
Nos acercamos a la versión final. Se corrigen Bug’s, errores, se implementan
mejoras de código y se modifica la cara de la aplicación.
- Modificamos el nombre y logo de la aplicación.
- Se implementa el menú final de la aplicación.
- Por primera vez tenemos un mapa
completamente funcional y sin BUG’s ni errores.
- Por primera tenemos una comunicación solida
con Mapa y Categorías.
- Se centralizan todos los textos para
compatibilizar una futura traducción automática de
la aplicación.
- Se depura código y se centraliza la enorme
cantidad de datos locales de Categorías para facilitar
su exportación a una base de datos en un futuro.
- Se reestructura y simplifica el complejo código
de Mapa y se reduce el código en un 50% de
Categorías.

## Licencia

This license lets others distribute, remix, adapt, and build upon your work, even commercially, as long as they credit you for the original creation. This is the most accommodating of licenses offered. Recommended for maximum dissemination and use of licensed materials.

