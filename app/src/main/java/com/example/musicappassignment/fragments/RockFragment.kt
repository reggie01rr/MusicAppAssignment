//package com.example.musicappassignment.fragments
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.musicappassignment.R
//import com.example.musicappassignment.fragments.model.ApiService
//import com.example.musicappassignment.fragments.model.SongsChannel
//
//class RockFragment : Fragment() {
//
//    private lateinit var musicList: RecyclerView
//    private lateinit var adapter: MusicAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
//
//        val view = inflater.inflate(
//            R.layout.music_list_fragment_layout,
//            container,
//            false
//        )
//        initViews(view)
//        getMusic()
//
//        return view
//    }
//
//
//    private fun getRockMusic(){
//
//        ApiService.initRetrofit().getMusic()
//            .enqueue(
//                object : Callback<SongsChannel> {
//                    override fun onResponse(
//                        call: Call<SongsChannel>,
//                        response: Response<SongsChannel>
//                    ) {
//                        Log.d(TAG, "onResponse: $response")
//                        if (response.isSuccessful) {
//                            updateAdapter(response.body())
//                        } else
//                            showError(response.message())
//                    }
//
//                    override fun onFailure(call: Call<SongsChannel>, t: Throwable) {
//                        Log.d(TAG, "onFailure: $t")
//                        showError(t.message ?: "Unknown error")
//                    }
//
//                }
//            )
//
//    }
//
//    private fun showError(errorMessage: String) {
//
//    }
//
//    private fun updateAdapter(body: SongsChannel) {
//        body?.let {
//            it.results.let { MusicList ->
//                adapter = MusicAdapter(MusicList) { MusicResponse ->
//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(MusicResponse.previewUrl))
//                    intent.setDataAndType(Uri.parse(MusicResponse.previewUrl), "video/mp4")
//                    activity?.startActivity(intent)
//                }
//                musicList.adapter = adapter
//            }
//        } ?: showError("No response from server")
//    }
//
//
//}





package com.example.musicapp.view

import android.os.Bundle
import MeAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.musicappassignment.R
import com.example.musicappassignment.fragments.model.ApiService
//import com.example.musicappassignment.fragments.model.SongList
import com.example.musicappassignment.fragments.model.SongsChannel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val TAG = "RockFragment"
class RockFragment: Fragment() {

    private lateinit var musicList: RecyclerView
    private lateinit var adapter: MeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(
            R.layout.fragment_rock,
            container,
            false
        )
        initViews(view)
        getMusic()

        return view
    }

    private fun initViews(view: View) {
        musicList = view.findViewById(R.id.rv_RockMusic)

        musicList.layoutManager = LinearLayoutManager(context)

        //Swipe Refresh
 //       val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
//        swipeRefreshLayout?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
//            Toast.makeText(activity, "Refreshed!", Toast.LENGTH_SHORT).show()
//            if (swipeRefreshLayout.isRefreshing){
//                TimeUnit.SECONDS.sleep(1L)
//                swipeRefreshLayout.isRefreshing = false
//            }
//        })
    }

    private fun getMusic() {
        ApiService.initRetrofit().getRockMusic("rock", "music", "song", "50")
            .enqueue(
                        object : Callback<SongsChannel>{
                      override fun onResponse(
                        call: Call<SongsChannel>,
                        response: Response<SongsChannel>
                    ) {
                        Log.d(TAG, "onResponse: $response")
                        if (response.isSuccessful) {
                            updateAdapter(response.body())
                        } else
                            showError(response.message())
                    }

                    override fun onFailure(call: Call<SongsChannel>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        showError(t.message ?: "Unknown error")
                    }

                }
            )
    }

    private fun showError(errorMessage: String) {

    }

    private fun updateAdapter(body: SongsChannel?) {
        body?.let {

                adapter = MeAdapter(it.results)
                musicList.adapter = adapter

        } ?: showError("No response from server")
    }
}