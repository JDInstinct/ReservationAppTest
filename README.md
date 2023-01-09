# API Documentation

# Entities

1. [Room](#room)
2. [Reservation](#reservation)

## Room

#### Available actions:

1. [Get All](#get_all)
2. [Get by ID](#get_by_ID)
3. [Add](#add)
4. [Delete](#delete_room)

### Get_All

Get all rooms

#### URL

`GET {host}/rooms`

#### Required Path Variables


`None`

#### Optional Path Variables


`None`

#### Required Request Body Parameters

`None`

#### Optional Request Body Parameters

`None`

#### Response Fields

| Parameter                         | Type            | Description                                                            |
|:----------------------------------|:----------------|:-----------------------------------------------------------------------|
| `*`                               | `Room[]`        | List of all Rooms                                                      |
| `*.id`                            | `string`        | Room ID                                                                |
| `*.name`                          | `string`        | Name of the room                                                       |
| `*.reservations`                  | `Reservation[]` | List of all reservations for this room                                 |
| `*.reservations.*.id`             | `string`        | Unique reservation id                                                  |
| `*.reservations.*.time`           | `string`        | Date and Time of reservation in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |
| `*.reservations.*.reason`         | `string`        | Reason for reservation                                                 |
| `*.reservations.*.duration`       | `int`           | Length of reservation in minutes                                       |
| `*.reservations.*.reserver`       | `object`        | Employee making the reservation                                        |
| `*.reservations.*.reserver.id`    | `string`        | Unique employee ID                                                     |
| `*.reservations.*.reserver.name`  | `string`        | Employee name                                                          |
| `*.reservations.*.reserver.email` | `string`        | Employee email                                                         |

##

### Get_by_ID

Get room by ID

#### URL

`GET {host}/rooms/{id}`

#### Required Path Variables


| Parameter | Type     | Description |
|:----------|:---------|:------------|
| `id`      | `string` | Room ID     |

#### Optional Path Variables


`None`

#### Required Request Body Parameters

`None`

#### Optional Request Body Parameters

`None`

#### Response Fields

| Parameter                         | Type            | Description                                                            |
|:----------------------------------|:----------------|:-----------------------------------------------------------------------|
| `*`                               | `Room[]`        | List of all Rooms                                                      |
| `*.id`                            | `string`        | Room ID                                                                |
| `*.name`                          | `string`        | Name of the room                                                       |
| `*.reservations`                  | `Reservation[]` | List of all reservations for this room                                 |
| `*.reservations.*.id`             | `string`        | Unique reservation id                                                  |
| `*.reservations.*.time`           | `string`        | Date and Time of reservation in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |
| `*.reservations.*.reason`         | `string`        | Reason for reservation                                                 |
| `*.reservations.*.duration`       | `int`           | Length of reservation in minutes                                       |
| `*.reservations.*.reserver`       | `object`        | Employee making the reservation                                        |
| `*.reservations.*.reserver.id`    | `string`        | Unique employee ID                                                     |
| `*.reservations.*.reserver.name`  | `string`        | Employee name                                                          |
| `*.reservations.*.reserver.email` | `string`        | Employee email                                                         |

##

### Add

Add a new room

#### URL

`POST {host}/rooms`

#### Required Path Variables


`None`

#### Optional Path Variables


`None`

#### Required Request Body Parameters

| Parameter | Type     | Description      |
|:----------|:---------|:-----------------|
| `name`    | `string` | Name of the room |

#### Optional Request Body Parameters

| Parameter                       | Type            | Description                                                            |
|:--------------------------------|:----------------|:-----------------------------------------------------------------------|
| `reservations`                  | `Reservation[]` | List of all reservations for this room                                 |
| `reservations.*.id`             | `string`        | Unique reservation id                                                  |
| `reservations.*.time`           | `string`        | Date and Time of reservation in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |
| `reservations.*.reason`         | `string`        | Reason for reservation                                                 |
| `reservations.*.duration`       | `int`           | Length of reservation in minutes                                       |
| `reservations.*.reserver`       | `object`        | Employee making the reservation                                        |
| `reservations.*.reserver.id`    | `string`        | Unique employee ID                                                     |
| `reservations.*.reserver.name`  | `string`        | Employee name                                                          |
| `reservations.*.reserver.email` | `string`        | Employee email                                                         |

#### Response Fields

| Parameter                       | Type            | Description                                                            |
|:--------------------------------|:----------------|:-----------------------------------------------------------------------|
| `id`                            | `string`        | Room ID                                                                |
| `name`                          | `string`        | Name of the room                                                       |
| `reservations`                  | `Reservation[]` | List of all reservations for this room                                 |
| `reservations.*.id`             | `string`        | Unique reservation id                                                  |
| `reservations.*.time`           | `string`        | Date and Time of reservation in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |
| `reservations.*.reason`         | `string`        | Reason for reservation                                                 |
| `reservations.*.duration`       | `int`           | Length of reservation in minutes                                       |
| `reservations.*.reserver`       | `object`        | Employee making the reservation                                        |
| `reservations.*.reserver.id`    | `string`        | Unique employee ID                                                     |
| `reservations.*.reserver.name`  | `string`        | Employee name                                                          |
| `reservations.*.reserver.email` | `string`        | Employee email                                                         |

##

### Delete_Room

Delete room by ID

#### URL

`DELETE {host}/rooms/{id}`

#### Required Path Variables


| Parameter | Type     | Description |
|:----------|:---------|:------------|
| `id`      | `string` | Room ID     |

#### Optional Path Variables


`None`

#### Required Request Body Parameters

`None`

#### Optional Request Body Parameters

`None`


#### Response Fields

| Parameter | Type     | Description          |
|:----------|:---------|:---------------------|
| `message` | `string` | Confirmation message |

##

## Reservation

#### Available actions:

1. [Get Reservations](#Get_Reservations)
2. [Add Reservation](#Add_Reservation)
3. [Get by Date](#Get_by_Date)
4. [Get by Date and Start Time](#Get_by_Date_and_Start_Time)
5. [Delete](#Delete_Reservation)

### Get_Reservations

Get all reservations for a specific room

#### URL

`GET {host}/rooms/{id}/reservations`

#### Required Path Variables


| Parameter | Type     | Description |
|:----------|:---------|:------------|
| `id`      | `string` | Room ID     |

#### Optional Path Variables


`None`

#### Required Request Body Parameters

`None`

#### Optional Request Body Parameters

`None`

#### Response Fields

| Parameter          | Type            | Description                                                            |
|:-------------------|:----------------|:-----------------------------------------------------------------------|
| `*`                | `Reservation[]` | List of all reservations for this room                                 |
| `*.id`             | `string`        | Unique reservation id                                                  |
| `*.time`           | `string`        | Date and Time of reservation in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |
| `*.reason`         | `string`        | Reason for reservation                                                 |
| `*.duration`       | `int`           | Length of reservation in minutes                                       |
| `*.reserver`       | `object`        | Employee making the reservation                                        |
| `*.reserver.id`    | `string`        | Unique employee ID                                                     |
| `*.reserver.name`  | `string`        | Employee name                                                          |
| `*.reserver.email` | `string`        | Employee email                                                         |

##

### Add_Reservation

Add a new reservation to a specific room

#### URL

`POST {host}/rooms/{id}/reservations`

#### Required Path Variables


| Parameter | Type     | Description |
|:----------|:---------|:------------|
| `id`      | `string` | Room ID     |

#### Optional Path Variables


`None`

#### Required Request Body Parameters

| Parameter        | Type     | Description                                                            |
|:-----------------|:---------|:-----------------------------------------------------------------------|
| `time`           | `string` | Date and Time of reservation in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |
| `reserver`       | `object` | Employee making the reservation                                        |
| `reserver.id`    | `string` | Unique employee ID                                                     |
| `reserver.name`  | `string` | Employee name                                                          |
| `reserver.email` | `string` | Employee email                                                         |

#### Optional Request Body Parameters

| Parameter        | Type     | Description                                                            |
|:-----------------|:---------|:-----------------------------------------------------------------------|
| `reason`         | `string` | Reason for reservation                                                 |
| `duration`       | `int`    | Length of reservation in minutes                                       |

#### Response Fields

| Parameter        | Type     | Description                                                            |
|:-----------------|:---------|:-----------------------------------------------------------------------|
| `id`             | `string` | Unique reservation id                                                  |
| `time`           | `string` | Date and Time of reservation in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |
| `reason`         | `string` | Reason for reservation                                                 |
| `duration`       | `int`    | Length of reservation in minutes                                       |
| `reserver`       | `object` | Employee making the reservation                                        |
| `reserver.id`    | `string` | Unique employee ID                                                     |
| `reserver.name`  | `string` | Employee name                                                          |
| `reserver.email` | `string` | Employee email                                                         |

##

### Get_by_Date

Get a list reservations for a specific room and date

#### URL

`GET {host}/rooms/{id}/reservations/findByDate/{date}`

#### Required Path Variables


| Parameter | Type     | Description                                                           |
|:----------|:---------|:----------------------------------------------------------------------|
| `id`      | `string` | Room ID                                                               |
| `date`    | `string` | Date in ISO format (time will be ignored) `e.g. YYYY-MM-DDThh:mm:ssZ` |

#### Optional Path Variables


`None`

#### Required Request Body Parameters

`None`

#### Optional Request Body Parameters

`None`

#### Response Fields

| Parameter          | Type            | Description                                                            |
|:-------------------|:----------------|:-----------------------------------------------------------------------|
| `*`                | `Reservation[]` | List of all reservations for this room                                 |
| `*.id`             | `string`        | Unique reservation id                                                  |
| `*.time`           | `string`        | Date and Time of reservation in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |
| `*.reason`         | `string`        | Reason for reservation                                                 |
| `*.duration`       | `int`           | Length of reservation in minutes                                       |
| `*.reserver`       | `object`        | Employee making the reservation                                        |
| `*.reserver.id`    | `string`        | Unique employee ID                                                     |
| `*.reserver.name`  | `string`        | Employee name                                                          |
| `*.reserver.email` | `string`        | Employee email                                                         |

##

### Get_by_Date_and_Start_Time

Get a reservation for a specific room, date and time

#### URL

`GET {host}/rooms/{number}/reservations/findByDateAndTime/{date-time}`

#### Required Path Variables


| Parameter   | Type     | Description                                             |
|:------------|:---------|:--------------------------------------------------------|
| `id`        | `string` | Room ID                                                 |
| `date-time` | `string` | Date and Time in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |

#### Optional Path Variables


`None`

#### Required Request Body Parameters

`None`

#### Optional Request Body Parameters

`None`

#### Response Fields

| Parameter        | Type     | Description                                                            |
|:-----------------|:---------|:-----------------------------------------------------------------------|
| `id`             | `string` | Unique reservation id                                                  |
| `time`           | `string` | Date and Time of reservation in ISO format `e.g. YYYY-MM-DDThh:mm:ssZ` |
| `reason`         | `string` | Reason for reservation                                                 |
| `duration`       | `int`    | Length of reservation in minutes                                       |
| `reserver`       | `object` | Employee making the reservation                                        |
| `reserver.id`    | `string` | Unique employee ID                                                     |
| `reserver.name`  | `string` | Employee name                                                          |
| `reserver.email` | `string` | Employee email                                                         |

##

### Delete_Reservation

Delete reservation by ID

#### URL

`DELETE {host}/rooms/{roomID}/reservations/delete/{reservationID}`

#### Required Path Variables


| Parameter       | Type     | Description    |
|:----------------|:---------|:---------------|
| `roomID`        | `string` | Room ID        |
| `ReservationID` | `string` | Reservation ID |

#### Optional Path Variables


`None`

#### Required Request Body Parameters

`None`

#### Optional Request Body Parameters

`None`

#### Response Fields

| Parameter | Type     | Description          |
|:----------|:---------|:---------------------|
| `message` | `string` | Confirmation message |
