package com.hamid.example.covidapk.info.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hamid.example.covidapk.R
import com.hamid.example.covidapk.info.api.RetrofitClient
import com.hamid.example.covidapk.info.model.IndonesiaResponse
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.Internal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showIndonesia()
    }
    private fun showIndonesia(){
        RetrofitClient.instance.getIndonesia().enqueue(object :
            Callback<ArrayList<IndonesiaResponse>>{
            override fun onResponse(
                call: Call<ArrayList<IndonesiaResponse>>,
                response: Response<ArrayList<IndonesiaResponse>>
            ) {
                val indonesia : IndonesiaResponse? = response.body()?.get(0)
                val positive : String? = indonesia?.positif
                val hospital = indonesia?.dirawat
                val recover : String? = indonesia?.sembuh
                val death :String?=indonesia?.meninggal

                btnProvince.setOnClickListener{
                    Intent(this@MainActivity, ProvinceActivity::class.java).also {
                        startActivity(it)
                }
                }

                tvPositive.text = positive
                tvHospitalized.text = hospital
                tvRecover.text=recover
                tvDeath.text=death
            }

            override fun onFailure(call: Call<ArrayList<IndonesiaResponse>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"${t.message}",Toast.LENGTH_SHORT).show()
            }

        })
    }
}