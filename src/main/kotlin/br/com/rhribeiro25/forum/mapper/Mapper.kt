package br.com.rhribeiro25.forum.mapper

interface Mapper<T, U> {

    fun map(t: T): U

}
