package com.csapps.trendinggit

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class RunBlockingTest {


    fun startRunBlocking(){

        println("Before runBlocking")
        runBlocking {
            launch{
                delay(3000)
                println("Inside runBlocking")
            }
        }
        println("After runBlocking")
    }
}