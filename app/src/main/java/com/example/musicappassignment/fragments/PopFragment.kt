package com.example.musicapp.view

import MeAdapter
import android.os.Bundle
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
import com.example.musicappassignment.fragments.model.SongsChannel
//import com.example.musicappassignment.fragments.model.SongList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val TAG = "ClassicFragment"
class PopFragment: Fragment() {

    private lateinit var musicList: RecyclerView
    private lateinit var adapter: MeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(
            R.layout.fragment_pop,
            container,
            false
        )
        initViews(view)
        getMusic()

        return view
    }

    private fun initViews(view: View) {
        musicList = view.findViewById(R.id.rv_PopMusic)
        musicList.layoutManager = LinearLayoutManager(context)
    }


    private fun getMusic() {
        ApiService.initRetrofit().getPopMusic("pop", "music", "song", "50")
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