package id.luvie.luvieapps.adapter

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.luvie.luvieapps.DokterSpesialisActivity
import id.luvie.luvieapps.JadwalKonsultasiActivity
import id.luvie.luvieapps.KategoriActivity
import id.luvie.luvieapps.databinding.ItemMenuHomeBinding
import id.luvie.luvieapps.model.MenuHome

import kotlin.collections.ArrayList

class MenuHomeAdapter(private val activity: Activity?,
                      private val data: ArrayList<MenuHome>) : RecyclerView.Adapter<MenuHomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ItemMenuHomeBinding.inflate(
                        activity!!.layoutInflater, viewGroup,
                        false
                ))
    }

    override fun onBindViewHolder(v: ViewHolder, i: Int) {
        with(v){
            val item = data[i]

            binding.textNamaMitraHome.text = item.menu

            val hostImage = "https://luvie.co.id/img/menu/${item.icon}"
            Glide.with(activity!!).load(hostImage).into(binding.imgMain)

            binding.llmain.setOnClickListener {
                if (item.status != "0"){
                    when(i){
                        0-> activity.startActivity(Intent(activity, DokterSpesialisActivity::class.java))
                        1-> activity.startActivity(Intent(activity, KategoriActivity::class.java))
                        2-> {

                        }
                        3-> activity.startActivity(Intent(activity, JadwalKonsultasiActivity::class.java))
                    }
                }else{
                    androidx.appcompat.app.AlertDialog.Builder(activity)
                        .setTitle("Coming soon")
                        .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {

                            }

                        })
                        .show()
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return data.size ?: 0
    }

    /**
     * View holder to display each RecylerView item
     */
    inner class ViewHolder(binding: ItemMenuHomeBinding) :
            RecyclerView.ViewHolder(binding.root) {
        val binding: ItemMenuHomeBinding = binding

    }

}