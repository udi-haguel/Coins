package gini.udihaguel.day22coins.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _shouldMoveToCoinFragment = MutableLiveData<Boolean>()
    val shouldMoveToCoinFragment:LiveData<Boolean> get() = _shouldMoveToCoinFragment

    fun startDelayToChangeFragment(){
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            _shouldMoveToCoinFragment.postValue(true)
        }
    }
}