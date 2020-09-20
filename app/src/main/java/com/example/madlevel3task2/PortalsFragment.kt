package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        PortalAdapter(portals) { portal: Portal -> portalItemClicked(portal) }

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
//        val builder = CustomTabsIntent.Builder()
//// modify toolbar color
//        builder.setToolbarColor(ContextCompat.getColor(MainActivity, R.color.colorPrimary))
//// add share button to overflow men
//        builder.addDefaultShareMenuItem()
//// add menu item to oveflow
//        builder.addMenuItem("MENU_ITEM_NAME", pendingIntent)
//// show website title
//        builder.setShowTitle(true)
//// modify back button icon
//        builder.setCloseButtonIcon(bitmap)
//// menu item icon
//        builder.setActionButton(bitmap, "Android", pendingIntent, true)
//// animation for enter and exit of tab            builder.setStartAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
//        builder.setExitAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
//// check is chrome available
//        val packageName = customTabHelper.getPackageNameToUse(this, WEB_URL_TO_LAUNCH)
//        if (packageName == null)
//// if chrome not available open in web view
//        else {
//            customTabsIntent.intent.setPackage(packageName) customTabsIntent.launchUrl(this, Uri.parse(WEB_URL_TO_LAUNCH))
//        }
    }
}

