package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*
import androidx.fragment.app.setFragmentResult

const val REQ_PORTAL_KEY = "req_portal_key"
const val PORTAL_NAME = "arg_portal_name"
const val PORTAL_URL = "arg_portal_url"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_portal.setOnClickListener {
            onAddPortal()
        }
    }

    private fun onAddPortal() {
        // get the values from the filled in inputs
        val name = input_name.text.toString()
        val url = input_url.text.toString()

        if (name.isNotBlank() && url.isNotBlank()) {
            //set the data as fragmentResult, we are listening for REQ_PORTAL_KEY in RemindersFragment!
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(PORTAL_NAME, name), Pair(PORTAL_URL, url)))

            //"pop" the backstack, this means we destroy
            //this fragment and go back to the RemindersFragment
            findNavController().popBackStack()
        } else {
            Toast.makeText(
                activity,
                R.string.not_valid_portal, Toast.LENGTH_SHORT
            ).show()
        }
    }
}