package com.example.normalapp

class DriverRegValidation {

    fun DriverRegValidateFeild(testname:String,testbirth:String,testaddress:String,testgender:String,testemail:String,testvnumber:String,testvType:String,testvinsuarance:String):Boolean{
        if (testname.isEmpty()){
            return false
        }
        if (testbirth.isEmpty()){
            return false
        }
        if(testaddress.isEmpty()){
            return false
        }
        if(testgender.isEmpty()){
            return false
        }
        if(testemail.isEmpty()){
            return false
        }

        if(testvnumber.isEmpty()){
            return false
        }
        if(testvType.isEmpty()){
            return false
        }
        if(testvinsuarance.isEmpty()){
            return false
        }
        return true
    }
}