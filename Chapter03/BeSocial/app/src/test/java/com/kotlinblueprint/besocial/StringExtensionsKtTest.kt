package com.kotlinblueprint.besocial

import com.kotlinblueprint.besocial.extensions.getDateInHours
import junit.framework.Assert
import org.junit.Test

/**
 * Created by hardik.trivedi on 30/09/17.
 */
class StringExtensionsKtTest {

    @Test
    fun dateIsReturningDay() {
        Assert.assertEquals("2d", "Fri Sep 11 15:42:26 +0000 2017".getDateInHours())
    }


}

