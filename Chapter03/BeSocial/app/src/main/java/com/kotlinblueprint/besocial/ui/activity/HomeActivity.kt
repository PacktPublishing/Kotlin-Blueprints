package com.kotlinblueprint.besocial.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.kotlinblueprint.besocial.util.AppToolbar
import com.kotlinblueprint.besocial.R
import com.kotlinblueprint.besocial.ui.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.find


class HomeActivity : AppCompatActivity(), AppToolbar {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpToolbar()
        container.adapter = SectionsPagerAdapter(supportFragmentManager)

        val tabLayout = findViewById(R.id.tabs) as? TabLayout
        tabLayout?.setupWithViewPager(container)
    }
}
