package gini.udihaguel.day22coins.ui.coins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import gini.udihaguel.day22coins.adapters.CoinsRvAdapter
import gini.udihaguel.day22coins.databinding.FragmentCoinsBinding

class CoinsFragment : Fragment() {

    private var _binding:FragmentCoinsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CoinsFragment()
    }

    private lateinit var viewModel: CoinsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = CoinsViewModel()
        binding.rvCoins.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.coinsLiveData.observe(viewLifecycleOwner){
            it.let {
                if (it.containsKey("USD") && it.containsKey("ILS"))
                    binding.rvCoins.adapter = CoinsRvAdapter(it, it["USD"]!!, it["ILS"]!!)
            }
        }
    }
}