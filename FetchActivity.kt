package com.sandeepkambojiftmu.livedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class FetchActivity : AppCompatActivity() {
    lateinit var e6: EditText
    lateinit var e7: EditText
    lateinit var e8: EditText
    lateinit var e9: EditText
    lateinit var e10: EditText

    lateinit var s6: String
    lateinit var s7: String
    lateinit var s8: String
    lateinit var s9: String
    lateinit var s10: String

    lateinit var s2: String
    lateinit var s3: String
    lateinit var s4: String
    lateinit var s5: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)


        val fetch = findViewById<AppCompatButton>(R.id.fetch_Button)
        val appCompatButton2 = findViewById<AppCompatButton>(R.id.fetch_updateButton)
        val appCompatButton3 = findViewById<AppCompatButton>(R.id.fetch_deleteButton)
        val e6 = findViewById<EditText>(R.id.rollFetch)

        val e7 = findViewById<EditText>(R.id.fetch_password)
        val e8 = findViewById<EditText>(R.id.fetch_address)
        val e9 = findViewById<EditText>(R.id.fetch_mobile)
        val e10 = findViewById<EditText>(R.id.qualificationFetch)

        s6 = e6.text.toString()
        s7 = e7.text.toString()
        s8 = e8.text.toString()
        s9 = e9.text.toString()
        s10 = e10.text.toString()

        fetch.setOnClickListener {
            s6 = e6.text.toString()
            s7 = e7.text.toString()
            s8 = e8.text.toString()
            s9 = e9.text.toString()
            s10 = e10.text.toString()

            fetch()
        }
    }

    fun fetch() {
        s6=e6.text.toString()
        val DATA_URL="https://studentdatabase8194075507.000webhostapp.com/info/fetch.php"
        var rq = Volley.newRequestQueue(this@FetchActivity)
        val stringRequest = object : StringRequest(DATA_URL+s6,
            Response.Listener<String> { response ->

                JSONFETCH(response)
                Toast.makeText(applicationContext, "Fetch", Toast.LENGTH_LONG).show()

            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(applicationContext, "ok="+volleyError.message, Toast.LENGTH_LONG).show()
                }
            }){
        }
        rq.add(stringRequest)
    }
    fun JSONFETCH(response:String){

        try {
            val jb = JSONObject(response)
            val jr: JSONArray = jb.getJSONArray("result")
            val jb1 = jr.getJSONObject(0)
            s2 = jb1.getString("PASSWORD")
            s3 = jb1.getString("ADDRESS")
            s4 = jb1.getString("MOBILE")
            s5 = jb1.getString("QUALIFICATION")
        } catch (e: JSONException) {
        }
        e6.setText(s2)
        e7.setText(s3)
        e8.setText(s4)
        e9.setText(s5)
    }

    fun update(){

        var url="https://studentdatabase8194075507.000webhostapp.com/info/update.php"
        var rq=Volley.newRequestQueue(this@FetchActivity)
        val stringRequest=object :StringRequest(
            Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(applicationContext,"update...",Toast.LENGTH_LONG).show()
            },
        object :Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(applicationContext,"VolleyError",Toast.LENGTH_LONG).show()
            }
        }){
            @Throws(AuthFailureError::class)
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("r", s6)
                params.put("p", s7)
                params.put("n", s8)
                params.put("m", s9)
                params.put("a",s10)
                return params

            }
        }
        rq.add(stringRequest)
    }
    fun delete(){
        val url ="https://studentdatabase8194075507.000webhostapp.com/info/delete.php"
        var rq = Volley.newRequestQueue(this@FetchActivity)
        val stringRequest = object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(applicationContext, "delete", Toast.LENGTH_LONG).show()
            },
            object : Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(applicationContext,"VOLLEY ERROR", Toast.LENGTH_LONG).show()
                }
            }){
            @Throws(AuthFailureError::class)
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String,String>()
                params.put("r",s6)
                return params
            }

        }
        rq.add(stringRequest)
    }
}
