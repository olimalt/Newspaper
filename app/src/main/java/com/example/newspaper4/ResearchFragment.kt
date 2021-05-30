package com.example.newspaper4


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_research.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.withTestContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.wait
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResearchFragment : Fragment() {


    private lateinit var tvFrom: TextView
    private lateinit var tvTo: TextView
    private lateinit var btnGetDateFrom: Button
    private lateinit var btnGetDateTo: Button
    private lateinit var btnResearch: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_research, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragMgr: FragmentManager? = activity?.supportFragmentManager
        tvFrom = view.findViewById(R.id.tvFrom)
        tvTo = view.findViewById(R.id.tvTo)
        btnGetDateFrom = view.findViewById<Button>(R.id.btnGetDateFrom)
        btnGetDateTo = view.findViewById<Button>(R.id.btnGetDateTo)
        btnResearch = view.findViewById<Button>(R.id.btnResearch)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //Le spinner et ses valeurs
        val spinner: Spinner = view.findViewById(R.id.spinnerSortBy)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.sortBy,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.setSelection(2)



        btnGetDateFrom.setOnClickListener {
            val dpd = DatePickerDialog(
                view.context,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    btnGetDateFrom.text = "$mYear-$mMonth-$mDay"
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        btnGetDateTo.setOnClickListener {
            val dpd = DatePickerDialog(
                view.context,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    btnGetDateTo.text = "$mYear-$mMonth-$mDay"
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        btnResearch.setOnClickListener {

            var liste : List<Article>? = null
            val testr = Coroutines.main(view) {
                println(Coroutines.getlist())
                println("teeeesssstt")
            }





            val transaction = activity?.supportFragmentManager?.beginTransaction()
            if (transaction != null) {

                println(liste?.get(0))
                println("tessst")
                println(Coroutines.getlist())
                //val bundle = Bundle()
                //bundle.putString("",liste.)
                transaction.replace(R.id.fragmentContainer,ListFragment())
                transaction.disallowAddToBackStack()
                transaction.commit()

                var navigationview = view.findViewById<NavigationView>(R.id.bottomNavigationView)
                navigationview?.menu?.getItem(3)?.isChecked = true
            }


          /*  view.bottomNavigationView.loadFragment(ListFragment())
            ArtistArrayAdapter adapter = new GestionNewsAPIAdapter( artists);
            recyclerView = (RecyclerView) findViewById(R.id.cardList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
*/
        }


    }

    object Coroutines {


        fun main(view: View, work: suspend (() -> Unit)) {
            GlobalScope.launch {
                val dateFrom = view.findViewById<Button>(R.id.btnGetDateFrom)?.text.toString()
                val dateTo = view.findViewById<Button>(R.id.btnGetDateTo)?.text.toString()
                val title = view.findViewById<TextInputEditText>(R.id.title).text.toString()
                val sortBy = view.findViewById<Spinner>(R.id.spinnerSortBy)?.selectedItem.toString()
                //println(btnGetDateFrom)
                val api = GestionNewsAPI(dateFrom, dateTo, title, sortBy)
                api.makeRequest()
                setlist(api.getListArticles()!!)

            }


        }
        private var d : List<Article>? =null
        fun setlist(list : List<Article>){

            d = list
        }

        fun getlist(): List<Article>? {
            return d
        }



    }
}