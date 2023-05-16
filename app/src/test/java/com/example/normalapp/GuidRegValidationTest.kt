package com.example.normalapp

import org.junit.Assert.*

import org.junit.Test

class GuidRegValidationTest {

    @Test
    fun `empty fname return false`() {
        val GuidRegValidation = GuidRegValidation()
        val result= GuidRegValidation.GuidRegValidateFeild(
            "",
            "199921302566v",
            "0712476454v",
            "Bandarawela",
            "Badulla",
            "k@gmail.com",
            "English",
            "Bsc",
            "Kaveesha",
            "Boc",
            "Bandarawela",
            "0123454443v",

        )
       assertEquals(false,result)
    }
    @Test
    fun `empty nic return false`() {
        val GuidRegValidation = GuidRegValidation()
        val result= GuidRegValidation.GuidRegValidateFeild(
            "",
            "",
            "0712476454v",
            "Bandarawela",
            "Badulla",
            "k@gmail.com",
            "English",
            "Bsc",
            "Kaveesha",
            "Boc",
            "Bandarawela",
            "0123454443v",

            )
        assertEquals(false,result)
    }
    @Test
    fun `empty contact Number return false `() {
        val GuidRegValidation = GuidRegValidation()
        val result= GuidRegValidation.GuidRegValidateFeild(
            "",
            "199921302566v",
            "",
            "Bandarawela",
            "Badulla",
            "k@gmail.com",
            "English",
            "Bsc",
            "Kaveesha",
            "Boc",
            "Bandarawela",
            "0123454443v",

            )
        assertEquals(false,result)
    }
    @Test
    fun `empty branch return false`() {
        val GuidRegValidation = GuidRegValidation()
        val result= GuidRegValidation.GuidRegValidateFeild(
            "",
            "199921302566v",
            "0712476454v",
            "Bandarawela",
            "Badulla",
            "k@gmail.com",
            "English",
            "Bsc",
            "Kaveesha",
            "Boc",
            "",
            "0123454443v",

            )
        assertEquals(false,result)
    }
}