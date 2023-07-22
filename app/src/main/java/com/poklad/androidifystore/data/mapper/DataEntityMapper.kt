package com.poklad.androidifystore.data.mapper

//todo как название и сам интерфейс?
interface DataEntityMapper<T, V> {
    fun mapFromCached(type: T): V

    fun mapToCached(type: V): T
}

/*
interface Mapper<SRC, DST> {
    fun transform(data: SRC): DST
}*/
/*
fun ProductDtoItem.toProductEntity(): ProductEntity {
    return ProductEntity(
  .....
    )
}*/
