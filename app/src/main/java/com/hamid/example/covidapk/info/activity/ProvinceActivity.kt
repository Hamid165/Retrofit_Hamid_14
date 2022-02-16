package com.hamid.example.covidapk.info.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamid.example.covidapk.R
import com.hamid.example.covidapk.info.adapter.ProvinceAdapter
import com.hamid.example.covidapk.info.api.RetrofitClient
import com.hamid.example.covidapk.info.model.ProvinceResponce
import kotlinx.android.synthetic.main.activity_province.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ProvinceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_province)
        showProvince()
    }

    private fun showProvince() {
        rvProvince.setHasFixedSize(true)
        rvProvince.layoutManager=LinearLayoutManager(this)

        RetrofitClient.instance.getProvince().enqueue(object : retrofit2.Callback<ArrayList<ProvinceResponce>>{
            override fun onResponse(
                call: Call<ArrayList<ProvinceResponce>>,
                response: Response<ArrayList<ProvinceResponce>>
            ) {
                val list : ArrayList<ProvinceResponce>? = response.body()
                val  adapter : ProvinceAdapter? = list?.let { ProvinceAdapter(it) }
                rvProvince.adapter=adapter
            }

            override fun onFailure(call: Call<ArrayList<ProvinceResponce>>, t: Throwable) {
                Toast.makeText(this@ProvinceActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}