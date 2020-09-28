package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel3task2.models.Portal
import kotlinx.android.synthetic.main.fragment_portals.*
import kotlinx.coroutines.MainCoroutineDispatcher

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter =
        PortalAdapter(portals, ::portalItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        rvPortals.layoutManager = GridLayoutManager(context, 2)
        rvPortals.adapter = portalAdapter

        observeAddPortalResult()
        portalAdapter.notifyDataSetChanged()
    }

    // "Listen" for the REQ_PORTAL_KEY from the add portal fragment and check if the bundle returned a portal
    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            // Retrieve the name and url from the bundle
            val name = bundle.getString(PORTAL_NAME)
            val url = bundle.getString(PORTAL_URL)

            portals.add(Portal(name, url))
            portalAdapter.notifyDataSetChanged()

            Log.e("ReminderFragment", "Request triggered, but empty reminder text!")
        }
    }

    private fun portalItemClicked(portalItem: Portal) {
//        Toast.makeText(context, getString(R.string.app_name), Toast.LENGTH_LONG).show()

        val builder = CustomTabsIntent.Builder()
            // modify toolbar color
        builder.setToolbarColor(ContextCompat.getColor(requireActivity(), R.color.colorPrimary))
            // add share button to overflow men
        builder.addDefaultShareMenuItem()
            // show website title
        builder.setShowTitle(true)
        builder.setExitAnimations(requireActivity(), android.R.anim.fade_in, android.R.anim.fade_out)

        val customTabsIntent = builder.build()

        customTabsIntent.launchUrl(requireActivity(), Uri.parse(portalItem.url))
    }
}

