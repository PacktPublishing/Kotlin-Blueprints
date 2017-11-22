package com.kotlinblueprint.besocial.util

import android.support.v7.widget.Toolbar
import com.kotlinblueprint.besocial.R
import com.kotlinblueprint.besocial.ui.activity.ProfileActivity
import org.jetbrains.anko.startActivity

/**
 * Created by hardik.trivedi on 01/10/17.
 */
interface AppToolbar {
    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun setUpToolbar() {
        toolbarTitle=toolbar.context.getString(R.string.toolbar_title)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.inflateMenu(R.menu.menu_home)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuProfile -> toolbar.context.startActivity<ProfileActivity>()
            }
            true
        }
    }
}