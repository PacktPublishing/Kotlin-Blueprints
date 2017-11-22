package com.kotlinblueprint.besocial.extensions

import android.view.View

/**
 * Created by hardik.trivedi on 18/09/17.
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}