package com.example.newspaper4

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
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
    private var gson = Gson()


    //***Constructeur***//
    init {
        apiKey = "fb291664db1f489c8b390fc4fcc91dd8"
    }

    //***Fonctions***//
    fun makeRequest() {
        val okHTTpClient = OkHttpClient()
        val parsedResponse = parseResponse(okHTTpClient.newCall(createRequest()).execute())
        val requete: Requete = gson.fromJson(parsedResponse, Requete::class.java)

        //Si la requete c'est bien passé, on attribue les valeurs de la requête en tant que champs
        if (requete != null && requete.status == "ok"){
            statut = requete.status
            articles = requete.articles
            println(articles?.map { it.author })
        }
    }

    private fun createRequest(): Request {
        return Request.Builder()
            .url(url)
            .build()
    }
    fun getListArticles(): List<Article>? {
        print(articles)
        return articles
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