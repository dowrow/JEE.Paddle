# Tecnología Spring: Proyecto Padel

### Funcionalidad implementada

#### Feature.01 - Caducidad de entidades Token
* Añadir campo creationTimestamp
* Añadir método hasExpired()

#### Feature.02 - Eliminación de tokens en TokenDao
* Añadir método void deleteByUser(User user)

#### Feature.03 - Implementación de entidad Training
* Añadir entidad Training que se relaciona con:
 * Una reserva de pista
 * De 0 a 4 alumnos
* Añadir método Calendar getStarting()
* Añadir método Calendar getEnding()

#### Feature.04 - Gestión de entramientos en TrainingDao
* Creación de entrenamientos
* Elimininación de entrenamientos
* Listar entrenamientos existentes
* Añadir un alumno a un entrenamiento
* Eliminar un alumno de un entrenamiento
 
###### Autor: Diego Castaño Chillarón
