package com.arthlimchiu.basicdaggertutorial.util

import org.junit.Assert.*

import org.junit.Test
//jsontests
class apijsonTest {

    @Test
    fun checkingsizeofapijsonlist() {
        val apijson = apijson()
        assertEquals(3,apijson.apijson.size)

    }

    @Test
    fun failcasesizeofapijsonlist() {
        val apijson = apijson()
        assertEquals(4,apijson.apijson.size)

    }

    @Test
    fun checkingiffirstpageisnullorempty() {
        val apijson = apijson()
        assertEquals(false,apijson.json1.isEmpty())

    }

    @Test
    fun checkingifsecondpageisnullorempty() {
        val apijson = apijson()
        assertEquals(false,apijson.json2.isEmpty())

    }

    @Test
    fun checkingifthirdpageisnullorempty() {
        val apijson = apijson()
        assertEquals(false,apijson.json3.isEmpty())

    }


}