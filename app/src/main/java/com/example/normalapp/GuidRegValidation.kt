package com.example.normalapp

class GuidRegValidation {

    fun GuidRegValidateFeild(testfname:String,testnic:String,testcontactnum:String,testaddress:String,testdistrict:String,testemail:String,testlanguage:String,testspecelte:String,testdescription:String,testbank:String,testbranch:String,testaccnum:String):Boolean{
        if(testfname.isEmpty()){
            return false
        }
        if(testnic.isEmpty()){
            return false
        }
        if(testcontactnum.isEmpty()){
            return false
        }
        if(testaddress.isEmpty()){
            return false
        }
        if(testdistrict.isEmpty()){
            return false
        }
        if(testemail.isEmpty()){
            return false
        }
        if(testlanguage.isEmpty()){
            return false
        }
        if(testspecelte.isEmpty()){
            return false
        }
        if(testdescription.isEmpty()){
            return false
        }
        if(testbank.isEmpty()){
            return false
        }
        if(testbranch.isEmpty()){
            return false
        }
        if(testaccnum.isEmpty()){
            return false
        }
      return true
    }
}