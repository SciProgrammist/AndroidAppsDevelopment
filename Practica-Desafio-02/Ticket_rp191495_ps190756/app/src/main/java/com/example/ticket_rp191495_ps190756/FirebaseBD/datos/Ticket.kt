package com.example.ticket_rp191495_ps190756.FirebaseBD.datos

class Ticket {
    fun key(key: String?) {
    }
    var key: String? = ""
    var numeroTicket: String? = ""
    var titulo: String? = ""
    var descripcion: String? = ""
    var departamentoUsuario: String? = ""
    var autor: String? = ""
    var emailAutor: String? = ""
    var fechaCreacion: String? = ""
    var estado: String? = ""
    var fechaFinalizacion: String? = ""
    var tic: MutableMap<String, Boolean> = HashMap()

    constructor() {}

    constructor(
        key: String?,
        numeroTicket: String?,
        titulo: String?,
        descripcion: String?,
        departamentoUsuario: String?,
        autor: String?,
        emailAutor: String?,
        fechaCreacion: String?,
        estado: String,
        fechaFinalizacion: String?
    ) {
        this.key = key
        this.numeroTicket = numeroTicket
        this.titulo = titulo
        this.descripcion = descripcion
        this.departamentoUsuario = departamentoUsuario
        this.autor = autor
        this.emailAutor = emailAutor
        this.fechaCreacion = fechaCreacion
        this.estado = estado
        this.fechaFinalizacion = fechaFinalizacion
    }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "key" to key,
            "numeroTicket" to numeroTicket,
            "titulo" to titulo,
            "descripcion" to descripcion,
            "departamentoUsuario" to departamentoUsuario,
            "autor" to autor,
            "emailAutor" to emailAutor,
            "fechaCreacion" to fechaCreacion,
            "estado" to estado,
            "fechaFinalizacion" to fechaFinalizacion,
            "tic" to tic
        )
    }
}

// Enum para el estado del ticket
enum class EstadoTicket {
    ACTIVO, FINALIZADO
}