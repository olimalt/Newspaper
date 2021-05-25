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
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_research.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
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

    private lateinit var title : TextInputEditText
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

        //On créer les variables pour exploiter l'interface (liaison)
        title = view.findViewById(R.id.title)
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
                    btnGetDateFrom.text = "Date :$mDay/$mMonth/$mYear"
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
                    btnGetDateTo.text = "Date :$mDay/$mMonth/$mYear"
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        btnResearch.setOnClickListener {
            Coroutines.main { }

        }
    }

    object Coroutines {

        fun main(work: suspend (() -> Unit)) {
            GlobalScope.launch {
                //var titleTexte = view.findViewById(R.id.title)
            val api = GestionNewsAPI() //TODO
            api.makeRequest()
            }
        }
    }
}



