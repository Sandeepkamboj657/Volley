package com.sandeepkambojiftmu.livedatabase

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RegistrationPage  : AppCompatActivity() {

    lateinit var e1: EditText
    lateinit var e2: EditText
    lateinit var e3: EditText
    lateinit var e4: EditText
    lateinit var e5: EditText

    lateinit var s1: String
    lateinit var s2: String
    lateinit var s3: String
    lateinit var s4: String
    lateinit var s5: String

    lateinit var reg:Button
    lateinit var showrecordb:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)

        e1 = findViewById<EditText>(R.id.regi_roll_num)
        e2 = findViewById<EditText>(R.id.regi_pwd)
        e3 = findViewById<EditText>(R.id.regi_address)
        e4 = findViewById<EditText>(R.id.regi_mobile)
        e5 = findViewById<EditText>(R.id.regi_qualifications)
        /* val reg = findViewById<Button>(R.id.regi_button_registration)
        val shows = findViewById<Button>(R.id.regi_button_show_details)*/


        reg.setOnClickListener {
            s1 = e1.text.toString()
            s2 = e2.text.toString()
            s3 = e3.text.toString()
            s4 = e4.text.toString()
            s5 = e5.text.toString()

            register()
        }
    }


        fun register() {
            var rq = Volley.newRequestQueue(this@RegistrationPage)
            var url = "https://studentdatabase8194075507.000webhostapp.com/info/register.php"
            val stringRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->

                    val intent = Intent(this, FetchActivity::class.java)
                    startActivity(intent)

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
                    params.put("m", s3)
                    params.put("a", s4)
                    params.put("q", s5)
                    return params
                }
            }
            rq.add(stringRequest)
        }
    }
