package com.poklad.androidifystore.data.mapper

interface Mapper<Source, Destination> {
    fun map(data: Source): Destination
}
