package coder.seventy.two.fapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import coder.seventy.two.fapp.helper.H
import coder.seventy.two.fapp.models.Token
import coder.seventy.two.fapp.services.ServiceBuilder
import coder.seventy.two.fapp.services.WebService
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {

        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()
        val apikey = etApkey.text.toString()



        if (email.isNotEmpty() && pass.isNotEmpty() && apikey.isNotEmpty()) {

            val service: WebService = ServiceBuilder.buildService(WebService::class.java)
            val loginService: Call<Token> = service.userLogin(email, pass, apikey)

            loginService.enqueue(object : Callback<Token> {
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    toast(t.message!!)
                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {

                    if (response.isSuccessful) {
                        val token: Token = response.body()!!
                        H.token = token.token
                        startActivity(Intent(this@MainActivity, Products::class.java))

                    } else {
                        toast("Something is not right on server!")
                    }

                }

            })


        } else {
            toast("Please fill all required fields!")
        }
    }
}
