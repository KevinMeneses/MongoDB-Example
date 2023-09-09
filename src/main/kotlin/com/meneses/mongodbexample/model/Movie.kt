package com.meneses.mongodbexample.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("movie")
@Serializable
data class Movie(
    @Id
    @Contextual
    val id: ObjectId? = null,
    @SerialName("Title")
    val title: String?,
    @SerialName("Year")
    val year: Long?,
    @SerialName("Released")
    val released: Released?,
    @SerialName("Runtime")
    val runtime: String?,
    @SerialName("Genre")
    val genre: List<String>?,
    @SerialName("Directors")
    val directors: List<String>?,
    @SerialName("Writers")
    val writers: List<Writer>?,
    @SerialName("Actors")
    val actors: List<String>?,
    @SerialName("Plot")
    val plot: String?,
    @SerialName("Languages")
    val languages: List<String>?,
    @SerialName("Country")
    val country: String?,
    @SerialName("Awards")
    val awards: String?,
    @SerialName("Poster")
    val poster: String?,
    @SerialName("Ratings")
    val ratings: List<Rating>?,
    @SerialName("Metascore")
    val metascore: Long?,
    @SerialName("Imdb")
    val imdb: Imdb?,
    @SerialName("BoxOffice")
    val boxOffice: Long? = null,
    @SerialName("Production")
    val production: String?,
    @SerialName("Website")
    val website: String? = null
)

@Serializable
data class Imdb(
    val imdbRating: Double?,
    val imdbVotes: Long?,
    val imdbID: String?
)

@Serializable
data class Rating(
    @SerialName("Source")
    val source: String?,
    @SerialName("Value")
    val value: Long?
)

@Serializable
data class Released(
    val date: String?
)

@Serializable
data class Writer(
    @SerialName("Name")
    val name: String?,
    @SerialName("Contribution")
    val contribution: String? = null
)