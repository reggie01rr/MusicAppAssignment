package com.example.musicappassignment.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.musicapp.view.ClassicFragment
import com.example.musicapp.view.PopFragment
import com.example.musicapp.view.RockFragment
import com.example.musicappassignment.MainActivity
import com.example.musicappassignment.fragments.model.Property


@Suppress("DEPRECATION")
internal class MyAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                RockFragment()
            }
            1 -> {
                ClassicFragment()
            }
            2 -> {
                PopFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return 3
    }
    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Rock"
            }
            1 -> {
                return "Classic"
            }
            2 -> {
                return "Pop"
            }
        }
        return super.getPageTitle(position)
    }
}