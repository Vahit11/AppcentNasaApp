package com.vahitkeskin.appcentnasaapp.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vahitkeskin.appcentnasaapp.R
import com.vahitkeskin.appcentnasaapp.databinding.ItemNasaBinding
import com.vahitkeskin.appcentnasaapp.model.Photo

class NasaAdapter(
    private val photoList: ArrayList<Photo>
) :
    RecyclerView.Adapter<NasaAdapter.NasaViewHolder>() {

    //We have to say that we are using dataBinding to use xml and adapter connection.
    class NasaViewHolder(var view: ItemNasaBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemNasaBinding>(inflater, R.layout.item_nasa, parent, false)
        return NasaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {

        //Combined adapter and xml in one line with DataBinding.
        holder.view.photo = photoList[position]

        //Open a dialog for each piece clicked.
        holder.itemView.setOnClickListener {
            val myContext = it.context
            detailsPopup(photoList[position], myContext)
        }

    }

    private fun detailsPopup(photo: Photo, context: Context) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.photo_details_popup)

        val ivPopupImgSrc = dialog.findViewById<ImageView>(R.id.ivPopupImgSrc)
        val tvPopupId = dialog.findViewById<TextView>(R.id.tvPopupId)
        val tvPopupSol = dialog.findViewById<TextView>(R.id.tvPopupSol)
        val tvPopupEarthDate = dialog.findViewById<TextView>(R.id.tvPopupEarthDate)
        val pbPopup = dialog.findViewById<ProgressBar>(R.id.pbPopup)

        val tvPopupCameraId = dialog.findViewById<TextView>(R.id.tvPopupCameraId)
        val tvPopupCameraName = dialog.findViewById<TextView>(R.id.tvPopupCameraName)
        val tvPopupCameraRoverId = dialog.findViewById<TextView>(R.id.tvPopupCameraRoverId)
        val tvPopupCameraFullName = dialog.findViewById<TextView>(R.id.tvPopupCameraFullName)

        val tvPopupRoverId = dialog.findViewById<TextView>(R.id.tvPopupRoverId)
        val tvPopupRoverName = dialog.findViewById<TextView>(R.id.tvPopupRoverName)
        val tvPopupRoverLandingDate = dialog.findViewById<TextView>(R.id.tvPopupRoverLandingDate)
        val tvPopupRoverLaunchDate = dialog.findViewById<TextView>(R.id.tvPopupRoverLaunchDate)
        val tvPopupRoverStatus = dialog.findViewById<TextView>(R.id.tvPopupRoverStatus)

        tvPopupId.text = photo.id.toString()
        tvPopupSol.text = photo.sol.toString()
        tvPopupEarthDate.text = photo.earth_date.toString()

        tvPopupCameraId.text = photo.camera?.id.toString()
        tvPopupCameraName.text = photo.camera?.name.toString()
        tvPopupCameraRoverId.text = photo.camera?.rover_id.toString()
        tvPopupCameraFullName.text = photo.camera?.full_name.toString()

        tvPopupRoverId.text = photo.rover?.id.toString()
        tvPopupRoverName.text = photo.rover?.name.toString()
        tvPopupRoverLandingDate.text = photo.rover?.landing_date.toString()
        tvPopupRoverLaunchDate.text = photo.rover?.launch_date.toString()
        tvPopupRoverStatus.text = photo.rover?.status.toString()

        //Image download from the internet with Picasso
        //The progressBar is hidden when the download is complete
        Picasso.get().load(photo.img_src).fit().centerCrop().into(
            ivPopupImgSrc,
            object : Callback {
                override fun onSuccess() {
                    pbPopup.isVisible = false
                }

                override fun onError(e: Exception?) {
                    e?.localizedMessage
                }

            })

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    //Receiving data received with LiveData on the adapter
    fun updatePhotoList(newPhotoList: List<Photo>) {
        photoList.clear()
        photoList.addAll(newPhotoList)
        notifyDataSetChanged()
    }
}