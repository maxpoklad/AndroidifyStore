package com.poklad.androidifystore.utils

sealed class Resource() {
    class Success<T>(data: T) : Resource()
    class Error(val throwable: Throwable?) : Resource()
    //todo а может нужні данные чтобы отображать что-то даже если не дозвонились до сервера
    //если во вьюмодел, то норм, если будет в Usecasr  то нужно данные передавать вроде.
    object Loading : Resource()
}