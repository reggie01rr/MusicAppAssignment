import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicappassignment.R


import com.example.musicappassignment.fragments.model.Property
//import com.example.musicappassignment.fragments.model.SongList
import com.example.musicappassignment.fragments.model.SongsChannel
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.io.IOException

var mMediaPlayer: MediaPlayer? = null

class MeAdapter(private val data: List<Property>) : RecyclerView.Adapter<MeAdapter.MyViewHolder>()  {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
       private val artistName = view.findViewById<TextView>(R.id.tvArtistName)
        private val tvArtworkUrl60 = view.findViewById<ImageView>(R.id.tvArtworkUrl60)
        private val trackPrice = view.findViewById<TextView>(R.id.tvTrackPrice)
       private val collectionName = view.findViewById<TextView>(R.id.collectionName)
        private val cardViewSongs = view.findViewById<View>(R.id.cvMusic)

        fun onBind(property: Property){

            collectionName.text = property.trackName
            artistName.text = property.artistName
            trackPrice.text =  property.trackPrice.toString() + "USD"
            Picasso.get().load(property.artworkUrl60).into(tvArtworkUrl60)

            Glide.with(view.context).load(property.artworkUrl60).centerCrop().into(tvArtworkUrl60)

            cardViewSongs.setOnClickListener {
                val link = property.previewUrl
                if(mMediaPlayer?.isPlaying == true)
                {  stopSongPlayer()
                }else{
                    startSongPlayer(link)
                    }
            }

            }



            private fun startSongPlayer(link: String) {

                try {
                    mMediaPlayer = MediaPlayer().apply {
                        setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                        )
                        setDataSource(link)
                        prepare()
                        start()
                    }
                } catch (exception: IOException) {
                    mMediaPlayer?.release()
                    mMediaPlayer = null
                }

            }




        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MeAdapter.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeAdapter.MyViewHolder {
//
//        return MeAdapter.MyViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    override fun onBindViewHolder(holder: MeAdapter.MyViewHolder, position: Int) {
//        holder.onBind(data[position])
//    }

    private fun stopSongPlayer(){
        mMediaPlayer?.stop()
    }
