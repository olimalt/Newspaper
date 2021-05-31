package com.example.newspaper4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccueilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccueilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accueil, container, false)
    }


    private var articles: List<Article>? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        //val adapter : GestionNewsAPIAdapter  = GestionNewsAPIAdapter( articles)
        //val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(LinearLayoutManager(view.context));
        val testr = AccueilFragment.Coroutines.main(view){}


    }
    object Coroutines {

        fun main(view: View ,work: suspend (() -> Unit)) {
            var articles : List<Article>? = null
            GlobalScope.launch{
                val api = GestionNewsAPI("", "", "", "")
                async { val test =api.getHeadlines()
                       articles=api.getListArticles()
                }.await()
                GlobalScope.launch(Dispatchers.Main) {
                    val adapter : GestionNewsAPIAdapter  = GestionNewsAPIAdapter( articles)
                    val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view);
                    //recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter)
                    recyclerView.setLayoutManager(LinearLayoutManager(view.context));
                }
            }


        }

    }
}



