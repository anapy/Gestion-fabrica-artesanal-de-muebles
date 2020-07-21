# Sistema-Integrado-de-Gestión-de-una-fabrica-artesanal-de-muebles

Los objetivos que se plantean en la realización de esta práctica son los siguientes:
• Familiarización con la Programación Orientada a Objetos (POO): definición de clases e instancias, uso
de la herencia, definición/uso de métodos estáticos y abstractos.
• Realización del diseño orientado a objetos de un problema.
• Implementación de un programa sencillo donde se manejen conceptos relacionados con POO.

4.1 Requerimientos de inicio
El main de la aplicación se encuentra en la clase Fabrica. A partir de ella se inicia la
consola con el interfaz textual. Se necesita un identificador para acceder a la aplicación
del tipo: C55555555. "C" en este caso identifica al usuario como cliente y el número que
sigue a la letra es el DNI del usuario.
Los diferentes tipos de usuarios son:
▪ C: cliente
▪ EA: empleado artesano
▪ EC: empleado comercial
▪ EJ: empleado jefe
▪ ADMIN

En el caso de no tener usuario se puede crear un nuevo usuario indicando” N” y acto
seguido introduciendo uno a uno los datos pedidos por pantalla.
Para moverse dentro de los menús, se muestran por pantalla unas opciones. Estas
opciones se pueden seleccionar introduciendo la letra elegida. La aplicación admite
tanto mayúsculas, como minúsculas.
Por ejemplo:
A- Hacer pedido
B- Listar pedidos
Indique una opción: B
Una vez que se ejecuta la opción elegida se vuelve de forma automática al menú que
corresponda (menú cliente, artesano, etc.).
4.2 Base de datos
Se ha creado una base de datos de usuarios que se inicia cuando se crea la consola
para que no sea necesario crear un usuario de cada para poder acceder a todos los
menús.
Es recomendable, acceder al menú ADMIN y elegir un usuario de cada categoría para
agilizar la prueba de la aplicación.
