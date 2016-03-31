# Tecnología Spring: Proyecto Padel

### Funcionalidad implementada

#### Caducidad de entidades Token
* Añadir campo creationTimestamp
* Añadir método hasExpired()

#### Eliminación de tokens en TokenDao
* Añadir método void deleteExpiredTokens()

#### Implementación de entidad Training
* Añadir entidad Training que consiste en:
 * Una pista
 * De 0 a 4 alumnos
 * Un entrenador
 * Una fecha y hora de comienzo
 * Una fecha y hora de fin

#### Gestión de entramientos en TrainingDao
* Creación de entrenamientos
* Elimininación de entrenamientos
* Listar entrenamientos existentes
* Añadir un alumno a un entrenamiento
* Eliminar un alumno de un entrenamiento

### Tests de la capa de persitencia
* Test para las entidades Token y Training
* Test para los daos TokenDao y TrainingDao

###  Caducidad de token en capa de negocio
* Modificación de UserDetailsServiceImpl para eliminar tokens caducados

### API Rest para gestionar clases de padel
* Implementación del recurso TrainingResource con los endpoints:
 * [/api/v0/trainings],methods=[POST]
 * [/api/v0/trainings],methods=[GET]
 * [/api/v0/trainings/{id}/pupils],methods=[GET]
 * [/api/v0/trainings/{id}/pupils],methods=[POST]
 * [/api/v0/trainings/{id}],methods=[DELETE]
 * [/api/v0/trainings/{id}/pupils/{pupil_id}],methods=[DELETE]
 * [/api/v0/users],methods=[POST]

### Tests para la capa de negocio
* Test funcionales para la el recurso TrainingResource


### Presentacion web con Thymeleaf
* Implementación de vista para mostrar entrenamientos disponibles.
* Implementación de vista para crear un nuevo entrenamiento

###### Autor: Diego Castaño Chillarón
