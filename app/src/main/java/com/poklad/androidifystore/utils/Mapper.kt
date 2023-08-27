package com.poklad.androidifystore.utils
interface Mapper<Source, Destination> {
    fun map(data: Source): Destination
}
