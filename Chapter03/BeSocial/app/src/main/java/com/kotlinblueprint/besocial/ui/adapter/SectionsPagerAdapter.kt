package com.kotlinblueprint.besocial.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kotlinblueprint.besocial.ui.fragment.HomeTimeLineFragment
import com.kotlinblueprint.besocial.ui.fragment.UserTimeLineFragment

/**
 * Created by hardik.trivedi on 15/09/17.
 */
/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeTimeLineFragment.newInstance()
        1 -> UserTimeLineFragment.newInstance()
        else -> {
            throw RuntimeException("No next fragments found, check your page count and number of fragments instantiated")
        }
    }

    override fun getCount(): Int = 2 // Show 3 total pages.

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Timeline"
            1 -> return "My Tweets"
        }
        return null
    }
}