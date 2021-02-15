package com.codeinsidecoffee

import android.content.Context
import android.content.SharedPreferences

class AppPrefrenceManager(var mSharedPreferences: SharedPreferences?=null, var context: Context?=null, var prefrenceName:String?=null,var prefrenceMode:Int?=null) {

    //TODO- Call by Prefence Object
    constructor(mSharedPreferences: SharedPreferences):this(mSharedPreferences,null,null,null)

    // TODO - Call by Prefrence Name Default Mode is Private
    constructor(context: Context,prefrenceName: String):this(null,context,prefrenceName,null)

    // TODO - Call by Prefrence Name and Prefrence Mode
    constructor(context: Context,prefrenceName: String,prefrenceMode: Int):this(null,context,prefrenceName,prefrenceMode)

    init {
        if (mSharedPreferences == null) {
            var prefMode=Context.MODE_PRIVATE
            if(prefrenceMode!=null)
               prefMode= prefrenceMode as Int

            mSharedPreferences = context!!.getSharedPreferences(prefrenceName, prefMode)
        }
    }
    fun setValue(key:String,value:Any){
        val editor=mSharedPreferences!!.edit()
        when(value){
            is String -> editor.putString(key,value) // TODO - Use to store String Value
            is Int -> editor.putInt(key,value)  // TODO - Use to store Integer Value
            is Long -> editor.putLong(key,value)  // TODO - Use to store Long Value
            is Float -> editor.putFloat(key,value)  // TODO - Use to store Float Value
            is Double -> editor.putLong(key,value.toBits())  // TODO - Use to store Double Value
            is Boolean -> editor.putBoolean(key,value)  // TODO - Use to store Boolean Value
            is Set<*> -> editor.putStringSet(key,value as Set<String>) // TODO - Use to store Set<String> Value
        }
        editor.apply()
    }

    fun getValue(key:String,defaultvalue:Any): Any? {
        when (defaultvalue){
            is String -> return mSharedPreferences!!.getString(key,defaultvalue) as String // TODO - Use to retrive String Value
            is Int -> return mSharedPreferences!!.getInt(key,defaultvalue) as Int // TODO - Use to retrive Integer Value
            is Long -> return mSharedPreferences!!.getLong(key,defaultvalue) as Long  // TODO - Use to retrive Long Value
            is Float -> return mSharedPreferences!!.getFloat(key,defaultvalue) as Float  // TODO - Use to retrive Float Value
            is Double -> return Double.fromBits(mSharedPreferences!!.getLong(key,(defaultvalue).toBits()) as Long)  // TODO - Use to retrive Double Value
            is Boolean -> return mSharedPreferences!!.getBoolean(key,defaultvalue) as Boolean  // TODO - Use to retrive Boolean Value
            is Set<*> -> return mSharedPreferences!!.getStringSet(key,defaultvalue as Set<String>) as Set<String>  // TODO - Use to retrive Set<String> Value
        }
        return null
    }

    fun clear(){ // TODO - it clear Prefrence Value
        mSharedPreferences!!.edit().clear().apply()
    }

    fun remove(key:String){  // TODO - helps to remove particular value based on prefrence keyname
        mSharedPreferences!!.edit().remove(key).apply()
    }
}