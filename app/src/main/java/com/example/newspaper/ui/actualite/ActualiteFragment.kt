package com.example.newspaper.ui.actualite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newspaper.R

class ActualiteFragment : Fragment() {

    private lateinit var actualiteViewModel: ActualiteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        actualiteViewModel =
                ViewModelProvider(this).get(ActualiteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_actualite, container, false)
        val textView: TextView = root.findViewById(R.id.text_actualite)
        actualiteViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}