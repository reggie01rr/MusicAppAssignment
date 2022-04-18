package com.example.musicappassignment.fragments.model

import com.example.musicappassignment.fragments.model.Property

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService{

    @GET(END_POINT)
    fun getRockMusic(@Query(PARAM_TERM) term: String,
                     @Query(PARAM_MEDIA) media: String,
                     @Query(PARAM_ENTITY) entity: String,
                     @Query(PARAM_LIMIT)  limit: String,): Call<SongsChannel> //You need this call SongsChannel

    @GET(END_POINT)
    fun getClassicMusic(@Query(PARAM_TERM) term: String,
                        @Query(PARAM_MEDIA) media: String,
                        @Query(PARAM_ENTITY) entity: String,
                        @Query(PARAM_LIMIT)  limit: String,): Call<SongsChannel>

    @GET(END_POINT)
    fun getPopMusic(@Query(PARAM_TERM) term: String,
                    @Query(PARAM_MEDIA) media: String,
                    @Query(PARAM_ENTITY) entity: String,
                    @Query(PARAM_LIMIT)  limit: String,): Call<SongsChannel>


    companion object{

        private const val BASE_URL = "https://itunes.apple.com/"
        private const val END_POINT = "search"
        private const val PARAM_TERM= "term"
        private const val PARAM_MEDIA = "amp;media"
        private const val PARAM_ENTITY = "amp;entity"
        private const val PARAM_LIMIT = "amp;limit"

        fun initRetrofit(): ApiService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

    }


}