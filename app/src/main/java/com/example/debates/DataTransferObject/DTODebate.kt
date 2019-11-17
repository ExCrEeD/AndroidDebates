package com.example.debates.DataTransferObject

import java.sql.Date

class DTODebate
{
    var Id:Int = 0
    lateinit var Titulo: String
    lateinit var Tema: String
    var Autor:Int = 0
    lateinit var AutorName:String
    lateinit var FechaPublicacion:String
    lateinit var FechaVencimiento:String
    var Estado:Boolean = false
    var Rate:Int = 0
    lateinit var Image: String
    lateinit var extensionImage: String
    lateinit var ImageByteArray:String
    var RatingCount:Int = 0

    fun haveImage():Boolean{
        return ::Image.isInitialized;
    }
}