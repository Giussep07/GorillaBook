package com.giussepr.gorillabook.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.giussepr.gorillabook.R
import com.giussepr.gorillabook.databinding.SplashFragmentBinding
import java.util.concurrent.TimeUnit

class SplashFragment : Fragment() {

    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.splash_fragment, container, false
        )

        Handler().postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFeedFragment())
        }, TimeUnit.SECONDS.toMillis(2))

        return binding.root
    }

}