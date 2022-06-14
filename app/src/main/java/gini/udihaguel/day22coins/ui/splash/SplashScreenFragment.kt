package gini.udihaguel.day22coins.ui.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import gini.udihaguel.day22coins.R
import gini.udihaguel.day22coins.ui.coins.CoinsFragment

class SplashScreenFragment : Fragment() {

    companion object {
        fun newInstance() = CoinsFragment()
    }
    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SplashScreenViewModel::class.java]

        viewModel.startDelayToChangeFragment()

        viewModel.shouldMoveToCoinFragment.observe(viewLifecycleOwner){
            if (it == true) {
                findNavController().navigate(R.id.coinsFragment)
            }
        }


    }

}