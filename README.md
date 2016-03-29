# Tecnología Spring: Proyecto Padel

### Funcionalidad implementada

#### Caducidad de entidades Token
* Añadir campo creationTimestamp
* Añadir método hasExpired()

#### Eliminación de tokens en TokenDao
* Añadir método void deleteByUser(User user)

#### Implementación de entidad Training
* Añadir entidad Training que consiste en:
 * Una pista
 * De 0 a 4 alumnos
 * Un entrenador
 * Una fecha de comienzo
 * Una fecha de fin
 * Un día y hora de la semana

#### Gestión de entramientos en TrainingDao
* Creación de entrenamientos
* Elimininación de entrenamientos
* Listar entrenamientos existentes
* Añadir un alumno a un entrenamiento
* Eliminar un alumno de un entrenamiento
 
###### Autor: Diego Castaño Chillarón
