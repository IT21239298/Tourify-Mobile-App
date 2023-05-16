package com.example.normalapp

import org.junit.Assert.*

import org.junit.Test

class DriverRegValidationTest {

    @Test
    fun`empty name return false`() {
        val DriverRegValidation = DriverRegValidation()
        val result=DriverRegValidation.DriverRegValidateFeild(
            "",
            "2023-07-31",
            "kurunagala",
            "male",
            "sathsara@gmail.com",
            "acd-1234",
            "car",
            "2024-07-31",

        )
        assertEquals(false,result)
    }

    @Test
    fun`empty dob return false`() {
        val DriverRegValidation = DriverRegValidation()
        val result=DriverRegValidation.DriverRegValidateFeild(
            "Sathsara",
            "",
            "kurunagala",
            "male",
            "sathsara@gmail.com",
            "acd-1234",
            "car",
            "2024-07-31",

            )
        assertEquals(false,result)
    }

    @Test
    fun`empty address return false`() {
        val DriverRegValidation = DriverRegValidation()
        val result=DriverRegValidation.DriverRegValidateFeild(
            "Sithum",
            "2023-07-31",
            "",
            "male",
            "sathsara@gmail.com",
            "acd-1234",
            "car",
            "2024-07-31",

            )
        assertEquals(false,result)
    }

    @Test
    fun`empty gender return false`() {
        val DriverRegValidation = DriverRegValidation()
        val result=DriverRegValidation.DriverRegValidateFeild(
            "Nisal",
            "2023-07-31",
            "kurunagala",
            "",
            "sathsara@gmail.com",
            "acd-1234",
            "car",
            "2024-07-31",

            )
        assertEquals(false,result)
    }

    @Test
    fun`empty email return false`() {
        val DriverRegValidation = DriverRegValidation()
        val result=DriverRegValidation.DriverRegValidateFeild(
            "Nisal",
            "2023-07-31",
            "kurunagala",
            "male",
            "",
            "acd-1234",
            "car",
            "2024-07-31",

            )
        assertEquals(false,result)
    }

    @Test
    fun`empty vehicle number return false`() {
        val DriverRegValidation = DriverRegValidation()
        val result=DriverRegValidation.DriverRegValidateFeild(
            "Nisal",
            "2023-07-31",
            "kurunagala",
            "",
            "sathsara@gmail.com",
            "",
            "car",
            "2024-07-31",

            )
        assertEquals(false,result)
    }

    @Test
    fun`empty vehicle type return false`() {
        val DriverRegValidation = DriverRegValidation()
        val result=DriverRegValidation.DriverRegValidateFeild(
            "Nisal",
            "2023-07-31",
            "kurunagala",
            "",
            "sathsara@gmail.com",
            "acd-1234",
            "",
            "2024-07-31",

            )
        assertEquals(false,result)
    }

    @Test
    fun`empty insurance return false`() {
        val DriverRegValidation = DriverRegValidation()
        val result=DriverRegValidation.DriverRegValidateFeild(
            "Nisal",
            "2023-07-31",
            "kurunagala",
            "",
            "sathsara@gmail.com",
            "acd-1234",
            "car",
            "",

            )
        assertEquals(false,result)
    }
}