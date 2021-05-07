package com.example.newspaper.ui.recherche

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newspaper.R

class RechercheFragment : Fragment() {

    private lateinit var rechercheViewModel: RechercheViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rechercheViewModel =
                ViewModelProvider(this).get(RechercheViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recherche, container, false)
        val textView: TextView = root.findViewById(R.id.text_recherche)
        rechercheViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}