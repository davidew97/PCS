package com.appserbabisa.app.data.local

import android.content.Context
import android.content.SharedPreferences
import com.appserbabisa.app.data.model.ActionState
import com.appserbabisa.app.data.model.AuthUser
import com.appserbabisa.app.util.getObject
import com.appserbabisa.app.util.putObject


class AuthPref(val context: Context) {
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences(AuthPref::class.java.name, Context.MODE_PRIVATE)
    }
    private companion object{
        const val AUTH_USER ="auth_user"
        const val IS_LOGIN ="is_login"
    }
    var authUser:AuthUser?
    get() = sp.getObject(AUTH_USER)
    private set(value) = sp.edit().putObject(AUTH_USER,value).apply()

    var islogin: Boolean
    get() = sp.getBoolean(IS_LOGIN,false)
    private set(value) = sp.edit().putBoolean(IS_LOGIN,value).apply()

    suspend fun login(email: String,passwor:String):ActionState<AuthUser>{
        val user = authUser
        return if(user==null){
            ActionState(message = "Anda belum terdaftar", isSucces = false)
        }else if (email.isBlank()||passwor.isBlank()){
            ActionState(message = "Email dan Password tidak boleh kosong", isSucces = false)
        }else if (user.email==email&&user.pass==passwor){
            islogin=true
            ActionState(authUser,message = "Anda berhasil login", isSucces = true)
        }else{
            ActionState(message = "Email atau Password salah",isSucces = false)
        }
    }
    suspend fun register( user: AuthUser):ActionState<AuthUser>{
        return if (user.email.isBlank()||user.pass.isBlank()){
            ActionState(message = "Email dan Password tidak boleh kosong", isSucces = false)
        }else{
            authUser=user
            ActionState(user,message = "Anda berhasil mendaftar")
        }
    }
    suspend fun logout():ActionState<Boolean>{
        islogin=false
        return ActionState(true,message = "Anda berhasil logout")
    }
}