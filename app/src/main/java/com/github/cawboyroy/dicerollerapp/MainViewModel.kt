package com.github.cawboyroy.dicerollerapp

import androidx.annotation.DrawableRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel (
    private val random: Random,
    private val communication: Communication
) : ViewModel() {

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) =
        communication.observe(owner, observer)

    fun changePicture() {
        @DrawableRes
        val imageResource: Int = when (random.value()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        communication.map(imageResource)
    }
}

interface Communication {

    fun observe(owner: LifecycleOwner, observer: Observer<Int>)

    fun map(data: Int)

    class Base : Communication {
        private val liveData = MutableLiveData<Int>()

        override fun observe(
            owner: LifecycleOwner,
            observer: Observer<Int>,
        ) = liveData.observe(owner, observer)


        override fun map(data: Int) {
            liveData.value = data
        }
    }
}

interface Random {
    fun value(): Int

    class Base(private val minimum: Int, private val maximum: Int) : Random {
        override fun value(): Int = (minimum..maximum).random()
    }
}

class Test (private val value: Int) : Random {
    override fun value(): Int = value
}