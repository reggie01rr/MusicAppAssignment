package com.example.musicappassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewpager.widget.ViewPager
import com.example.musicapp.view.RockFragment
import com.example.musicappassignment.fragments.MyAdapter
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = MyAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_home_foreground)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_home_foreground3)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_android_pink_24dp)
        /*supportFragmentManager.beginTransaction()
            .replace(
                com.google.android.material.R.id.container, RockFragment())
            .commit()*/
    }
}






//    lateinit var tabLayout: TabLayout
//    lateinit var viewPager: ViewPager
//
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var meAdapter: Adapter<*>
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        manager = LinearLayoutManager(this)
//       getAllData()
//
//        title = "MusicApp"
//        tabLayout = findViewById(R.id.tabLayout)
//        viewPager = findViewById(R.id.viewPager)
//        tabLayout.addTab(tabLayout.newTab().setText("Rock"))
//        tabLayout.addTab(tabLayout.newTab().setText("Classic"))
//        tabLayout.addTab(tabLayout.newTab().setText("Pop"))
//        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
//        val adapter = MyAdapter(this, supportFragmentManager,
//            tabLayout.tabCount)
//        viewPager.adapter = adapter
//        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {     viewPager.currentItem = tab.position
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab) {}
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//
//
//
//        })
//
//
//    }
//

//
//    fun getAllData(){
//        Api.retrofitService.getAllData().enqueue(object: Callback<SongsChannel> {
//            override fun onResponse(
//                call: Call<SongsChannel>,
//                response: Response<SongsChannel>
//            ) {
//                if(response.isSuccessful){
//                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply{
//                        //meAdapter = MyAdapter(response.body()!!)
//                        layoutManager = manager
//                        adapter = meAdapter
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<SongsChannel>, t: Throwable) {
//                t.printStackTrace()
//            }
//})
//    }
//}
