package com.meneses.mongodbexample

import com.meneses.mongodbexample.repository.MovieRepository
import com.meneses.mongodbexample.service.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class MongodbExampleApplication {

    @Autowired
    lateinit var movieRepository: MovieRepository

    fun run() {
        MovieService(movieRepository).run {
            println("\nExercise 1 & 2")
            populateDataBase()
            println("\nExercise 3")
            getMoviesReleasedSince2000()
            println("\nExercise 4")
            getMoviesWithImdbRatingGreaterThan75()
            println("\nExercise 5")
            getMoviesInEnglishAndFrench()
            println("\nExercise 6")
            getMoviesWhereStevenSpielbergScreenPlayed()
            println("\nExercise 7")
            getMoviesNoRated()

            println("\nExercise 9")
            getMoviesWithRatingsAverage()
        }
    }
}

fun main(args: Array<String>) {
    val applicationContext = runApplication<MongodbExampleApplication>(*args)
    val app = applicationContext.getBean(MongodbExampleApplication::class.java)
    app.run()
}
