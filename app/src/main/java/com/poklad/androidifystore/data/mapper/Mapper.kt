package com.poklad.androidifystore.data.mapper

interface Mapper<SRC, DST> {
    fun transform(data: SRC): DST
}
