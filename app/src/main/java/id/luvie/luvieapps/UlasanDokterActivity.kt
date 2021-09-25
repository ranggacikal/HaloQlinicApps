package id.luvie.luvieapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import id.luvie.luvieapps.databinding.ActivityUlasanDokterBinding
import org.json.JSONObject
import splitties.activities.start
import splitties.alertdialog.alertDialog
import splitties.alertdialog.message
import splitties.alertdialog.okButton
import splitties.toast.toast

class UlasanDokterActivity : AppCompatActivity() {
    lateinit var binding : ActivityUlasanDokterBinding
    var id_transaksi = "";var nama = ""
    var spesialis = "";var gambar = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUlasanDokterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            id_transaksi = bundle["id_transaksi"] as String
            nama = bundle["nama"] as String
            spesialis = bundle["spesialis"] as String
            gambar = bundle["gambar"] as String
        }

        binding.txtNama.text = nama
        binding.txtSpesialis.text = spesialis
        Glide.with(applicationContext).load(gambar).into(binding.avaMurid)

        binding.btnSubmit.setOnClickListener {
            val point = binding.rating.rating
            if (point.equals(0)) {
                alertDialog {
                    title = "Perhatian"
                    message = "Harap untuk memberi rating pada Dokter"
                    okButton { it.dismiss() }
                }.show()
            } else {
                val params = HashMap<String,String>()
                params["id_transaksi"] = id_transaksi
                params["nilai"] = binding.rating.rating.toString()
                params["komentar"] = binding.edtKomentar.text.toString()

                ratingDokter(params)
            }
        }

    }

    fun ratingDokter(params:HashMap<String,String>){
        val host = "https://luvie.co.id/android/customer/feedback.php"

        AndroidNetworking.post(host).addBodyParameter(params)
            .build().getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    start<MainActivity>()
                    finish()
                    toast("Berhasil memberi Rating pada Dokter")
                }

                override fun onError(anError: ANError?) {
                    toast("Gagal, Silahkan Coba lagi.")
                }
            })
    }

    override fun onBackPressed() {
        alertDialog {
            title = "Perhatian"
            message = "Harap untuk memberi rating pada Dokter"
            okButton { it.dismiss() }
        }.show()

    }
}