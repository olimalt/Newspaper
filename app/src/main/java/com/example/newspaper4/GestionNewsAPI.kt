package com.example.newspaper4

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

//https://kotlinlang.org/docs/classes.html
class GestionNewsAPI (datefrom : String,dateto : String,title: String,sortby: String) { //:  AsyncTask<String, String, String>()

    //***Champs***//
    private var apiKey: String = "" //Clef API Yanis
    private var url: String =
        "https://newsapi.org/v2/everything?q="+title+"&from="+datefrom+"&to="+dateto+"&sortBy="+sortby+"&apiKey=fb291664db1f489c8b390fc4fcc91dd8"
    private var statut: String? = null
    private var articles: List<Article>? = null

    //***Constructeur***//
    init {
        apiKey = "fb291664db1f489c8b390fc4fcc91dd8"
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