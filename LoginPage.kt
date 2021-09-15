package com.sandeepkambojiftmu.livedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {

    lateinit var e1:EditText
    lateinit var e2:EditText
    lateinit var s1:String
    lateinit var s2:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)


        val b1=findViewById<Button>(R.id.login_button_login)
        e1=findViewById<EditText>(R.id.login_Roll_num)
        e2=findViewById(R.id.login_password)

        s1=e1.text.toString()
        s2=e2.text.toString()

        b1.setOnClickListener {
            s1=e1.text.toString()
            s2=e2.text.toString()
            login()
        }



    }

    fun login(){
        var rq = Volley.newRequestQueue(this@LoginPage)
        var url = "https://studentdatabase8194075507.000webhostapp.com/info/login.php"
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
//t1.setText(response)
                if(response.contentEquals(response)){
                    val intent=Intent(this,LoginPage::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "login", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(applicationContext, "Not login", Toast.LENGTH_LONG).show()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("r", s1)
                params.put("p", s2)
                return params
            }
        }

//adding request to queue
        rq.add(stringRequest)


    }
}