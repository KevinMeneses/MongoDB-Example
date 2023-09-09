package com.meneses.mongodbexample.repository

import com.meneses.mongodbexample.model.Movie
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: MongoRepository<Movie, String> {

    @Query(value = "?0", fields = "?1")
    fun getByQuery(value: String, fields: String = ""): List<Movie>

    @Aggregation(pipeline = ["?0"])
    fun aggregateByCondition(condition: String): List<String>
}