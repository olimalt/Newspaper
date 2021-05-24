package com.example.newspaper4

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

//https://kotlinlang.org/docs/classes.html
class GestionNewsAPI { //:  AsyncTask<String, String, String>()

    //***Champs***//
    private var apiKey: String = "" //Clef API Yanis
    private var url : String = "https://newsapi.org/v2/everything?q=Apple&from=2021-05-23&sortBy=popularity&apiKey=fb291664db1f489c8b390fc4fcc91dd8"
    private var statut : String? = null
    private var articles : List<Article>? = null

    //***Constructeur***//
    init{
        apiKey = "fb291664db1f489c8b390fc4fcc91dd8"
    }

    //***Fonctions***//
    fun makeRequest(){
        val okHTTpClient = OkHttpClient()
        val parsedResponse = parseResponse(okHTTpClient.newCall(createRequest()).execute())
    }

    private fun createRequest(): Request {
        return Request.Builder()
            .url(url)
            .build()
    }

    private fun parseResponse(response: Response): String {
        println(response.body?.string())
        return response.body?.string() ?: ""
    }







    /*
    //Requête à l'api
    override fun doInBackground(vararg p0: String?): String {
        //Gestion de la date
        val formatteur = SimpleDateFormat("yyyy-MM-dd",  Locale.getDefault())
        val date = Date()
        val dateString = formatteur.format(date)
        //Requete a l'api
        val q = String();
        val urlRequete = "https://newsapi.org/v2/everything?q=$q&from=$dateString&sortBy=publishedAt&apiKey=$apiKey&language=en"
        val s = sendGet(urlRequete)

        return s
    }

    @Throws(IOException::class)
    private fun sendGet(url: String): String {
        val urlObject = URL(url)
        val connexion = urlObject.openConnection() as HttpURLConnection
        connexion.requestMethod = "GET"
        val responseCode = connexion.responseCode

        if (responseCode == HttpURLConnection.HTTP_OK) { // connection ok
            val reponse = StringBuffer()
            val buffer = BufferedReader(InputStreamReader(connexion.inputStream))

            var ligne : String?

            do {
                ligne = buffer.readLine()
                if (ligne == null)
                    break
                reponse.append(ligne)
            } while (true)

            buffer.close()
            return reponse.toString()
        } else {
            return ""
        }
    }

    override fun onPostExecute(result: String?) {

        if ( result != null ){

            parseResultatJson(result)
        }
    }


    private fun parseResultatJson(s: String) {

        val p = Gson()
        val ResultatRequeteNewsApiObjet = p.fromJson(s, ResultatRequeteNewsApi::class.java)

        if ( ResultatRequeteNewsApiObjet.statut == "ok" ){

            //newsFetchedListener?.whenNewsFetchedSuccessfully(ResultatRequeteNewsApiObjet.listeArticles)
            RequeteSucces();
        }
        else {

            //newsFetchedListener?.whenNewsFetchedOnError("Error")
            RequeteEchec();
        }
    }

    private fun RequeteSucces(){
        //TO DO => récupérer les articles sous forme de listeArticles
    }

    private fun RequeteEchec(){
        //TO DO => Afficher un message d'erreur
    }

    */
}