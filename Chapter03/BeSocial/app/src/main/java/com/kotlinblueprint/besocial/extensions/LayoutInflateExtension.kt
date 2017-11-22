package com.kotlinblueprint.besocial.extensions

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by hardik.trivedi on 01/10/17.
 */
fun ViewGroup?.inflate(layoutId: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(this?.context).inflate(layoutId, this, attachToRoot)