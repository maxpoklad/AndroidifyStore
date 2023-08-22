package com.poklad.androidifystore.utils
//todo where is the best place to store this interface or to write a different one in each layer?
interface Mapper<Source, Destination> {
    fun map(data: Source): Destination
}
