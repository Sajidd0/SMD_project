package com.example.testdemo

import android.widget.Toast

class UserHelper(val name:String,val email:String,val Password:String,val conPassword:String,val address:String,val Phone:String) {
    fun checkCredentials():Boolean{
        if((name.isEmpty())||email.isEmpty()||Password.isEmpty()||conPassword.isEmpty()||address.isEmpty()||Phone.isEmpty()){
            return false
        }
        if(Password.length<8){
            return false
        }
        if(Password!=conPassword){
            return false
        }
        return true
    }
}