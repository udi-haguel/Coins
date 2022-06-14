package gini.udihaguel.day22coins.ui.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gini.udihaguel.day22coins.network.SharedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Collectors.toList

class CoinsViewModel : ViewModel() {
    private val _coinsLiveData = MutableLiveData<Map<String,Double>>()
    val coinsLiveData:LiveData<Map<String,Double>> get() = _coinsLiveData

    private val repository = SharedRepository()


    init {
        getDataFromApi()
    }

    private fun getDataFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getCurrency()
            val rates = data.rates?.let {
                _coinsLiveData.postValue(it.toList().sortedBy { (_, value) -> value}.toMap())
            }
        }
    }
}