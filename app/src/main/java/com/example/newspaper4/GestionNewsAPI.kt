package com.example.newspaper4

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

//https://kotlinlang.org/docs/classes.html
class GestionNewsAPI (){   // (titre: String)

    //***Champs***//
    private var apiKey: String = "" //Clef API Yanis
    private var url: String = ""
    private var statut: String? = null
    private var articles: List<Article>? = null
    private var titreRecherche = "Apple"

    //***Constructeur***//
    init {
        apiKey = "fb291664db1f489c8b390fc4fcc91dd8"
        titreRecherche = "Apple" //titre
        url = "https://newsapi.org/v2/everything?q=${titreRecherche}Apple&from=2021-05-23&sortBy=popularity&apiKey=${apiKey}"
    }

    //***Fonctions***//
    fun makeRequest() {
        val okHTTpClient = OkHttpClient()
        val parsedResponse = parseResponse(okHTTpClient.newCall(createRequest()).execute())

    }

    private fun createRequest(): Request {
        return Request.Builder()
            .url(url)
            .build()
    }

    private fun parseResponse(response: Response): String? {
        val body = response.body?.string()

        println(body)
        if (body != null) {
            return body
        }
        else {
            return "false"
        }
    }
}