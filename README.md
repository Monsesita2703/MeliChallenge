# MeliChallenge

## Instalación
Clonar este repositorio e importarlo en **Android Studio**
```bash
git clone https://github.com/Monsesita2703/MeliChallenge.git
```
## Descripción

El siguiente repositorio cuenta con el código necesario para cumplir con lo descrito a continuación

MeliChallenge es una pequeña aplicación que permite a los usuarios buscar productos que obtiene por medio de un API de mercado libre y mostrarlos en forma de lista. 
Además, se pueden ver distintas características de los productos en la página del detalle del producto.
Como validaciones, si el usuario no ingresa nada en el campo del buscador, al dar clic se agrega un mensaje para indicarle que debe ingresar un producto. Por otro lado, si el usuario ingresa un producto inexistente, se le mostrará una pantalla indicando que no se encontraron resultados. Así mismo si ocurre un error al consultar los servicios, se mostrará una pantalla indicando al usuario que ocurrió un error. Finalmente, si el usuario no tuviera conexión a internet se mostrará un Toast indicando error y al dar clic en la búsqueda se muestra mensaje que indica que no se tiene conexión. 

## Arquitectura

La arquitectura elegida fue MVVM.

## Lenguaje

Kotlin

## Librerías utilizadas

* LiveData: Para encargarse de observar el cambio de estado de nuestros objetos.
* Navigation: Para las interacciones que tendrá el usuario en la aplicación.
* Retrofit: Utilizada para consumir el API de Mercado Libre
* Glide: Para mostrar las imágenes
* Hilt: Para inyección de dependencias

## Descripción de paquetes

A continuación se presenta una pequeña descripción sobre el contenido de cada paquete:
* data: En este paquete se encuentra todo lo necesario para la capa "Model" de la arquitectura, es decir, en ella encontraremos las clases 
que representan a nuestros objetos, y la fuente que nos permitirá obtener los datos, en este caso es la clase ItemRemoteDataSource ya que los datos los obtendremos de forma
remota desde un API. Así mismo tenemos aquellas clases necesarias para consumir el API, 
* repository: En este paquete que encontraremos en el paquete "data", tendremos a la clase ItemRepository la cual tiene como función manejar los datos que necesitamos. 
En este caso, dado que los datos solo se obtienen de forma remota, se encarga de recuperarlos para posteriormente regresarlos a la clase
correspondiente. Si en algún momento se deseará persistir los datos de forma local, en esta clase se agregarían aquellos métodos para indicarlo.
* di: En este paquete encontraremos a la clase AppModule en la que inyectaremos aquellas dependencias que usando a lo largo de la aplicación.
* ui: En este paquete encontraremos los subpaquetes que contendrán aquellas clases necesarias para cada vista requerida para el usuario. A su vez, en cada
subpaquete podremos encontrar las clases "Activity" o "Fragment" según se requiriera, así como las clases ViewModel las cuales se encargan de la comunicación entre las vistas y los modelos, por ejemplo la clase ItemSearchViewModel es la encargada de realizar las peticiones para obtener los resultados de la búsqueda del usuario
y así mismo comunicarle a la vista que el modelo ha cambiado para que ésta actualice lo presentado a usuario.
* utils: En este paquete encontraremos 3 clases principales. NetworkUtil es la que nos permitirá obtener el estado de la red del usuario. Resource es la clase
que nos ayudará a tratar de forma óptima la respuesta proporcionada por los API ya que en ella indicaremos los estados (SUCCESS, LOADING, ERROR)
de la vista que nos ayudará para saber qué debemos mostrar al usuario. Utils es la clase creada principalmente para aquellos métodos que nos ayudarán
a obtener los formatos de cadena para la presentación de la información de los items en la lista y el detalle.


