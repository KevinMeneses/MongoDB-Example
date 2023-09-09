package com.meneses.mongodbexample.service

import com.meneses.mongodbexample.model.Movie
import com.meneses.mongodbexample.repository.MovieRepository
import kotlinx.serialization.json.Json

class MovieService (
    private val repository: MovieRepository,
    private val converter: Json = Json { isLenient = true }
){
    fun populateDataBase() = with(repository) {
        deleteAll()
        val movies = listOf(
            "{\n" +
                    "  \"Title\": \"Ex Machina\",\n" +
                    "  \"Year\": 2014,\n" +
                    "  \"Released\": { \"date\": \"2015-04-24\" },\n" +
                    "  \"Runtime\": \"108 min\",\n" +
                    "  \"Genre\": [ \"Drama”, “Mystery”, “Sci-Fi\" ],\n" +
                    "  \"Directors\": [ \"Alex Garland\" ],\n" +
                    "  \"Writers\": [ { \"Name\": \"Alex Garland\" } ],\n" +
                    "  \"Actors\": [ \"Domhnall Gleeson\", \"Corey Johnson\", \"Oscar Isaac\", \"Alicia Vikander\" ],\n" +
                    "  \"Plot\": \"A young programmer is selected to participate in a ground-breaking experiment in synthetic intelligence by evaluating the human qualities of a breath-taking humanoid A.I.\",\n" +
                    "  \"Languages\": [ \"English\"  ],\n" +
                    "  \"Country\": \"UK\",\n" +
                    "  \"Awards\": \"Won 1 Oscar. Another 69 wins & 153 nominations.\",\n" +
                    "  \"Poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxNzc0OTIxMV5BMl5BanBnXkFtZTgwNDI3NzU2NDE@._V1_SX300.jpg\",\n" +
                    "  \"Ratings\": [ { \"Source\": \"Internet Movie Database\", \"Value\": 77 }, { \"Source\": \"Rotten Tomatoes\", \"Value\": 92 }, { \"Source\": \"Metacritic\", \"Value\": 78 } ],\n" +
                    "  \"Metascore\": 78,\n" +
                    "  \"Imdb\": { \"imdbRating\": 7.7, \"imdbVotes\": 348372, \"imdbID\": \"tt0470752\" },\n" +
                    "  \"BoxOffice\": 19012798,\n" +
                    "  \"Production\": \"A24 Films\",\n" +
                    "  \"Website\": \"http://meet-ava.com/\" \n" +
                    "}",
            "{\"Title\":\"Planet of the Apes\",\"Year\":2001,\"Released\":{\"date\":\"2001-07-27\"},\"Runtime\":\"119 min\",\"Genre\":[\"Action\", \"Adventure\", \"Sci-Fi\"],\"Directors\": [\"Tim Burton\"],\"Writers\":[{\"Name\":\"Pierre Boulle\", \"Contribution\":\"novel\"}, {\"Name\":\"William Broyles Jr.\", \"Contribution\":\"screenplay\"}, {\"Name\":\"Lawrence Konner\", \"Contribution\":\"screenplay\"}, {\"Name\":\"Mark Rosenthal\", \"Contribution\":\"screenplay\"}],\"Actors\":[\"Mark Wahlberg\", \"Tim Roth\", \"Helena Bonham Carter\", \"Michael Clarke Duncan\"], \"Plot\":\"An Air Force astronaut crash lands on a mysterious planet where evolved, talking apes dominate a race of primitive humans.\", \"Languages\":[\"English\"], \"Country\":\"USA\", \"Awards\":\"Nominated for 2 BAFTA Film Awards. Another 10 wins & 27 nominations.\", \"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BY2RlMDhlY2MtMjQ1Zi00NzI5LTgxOTgtZjliNWMzYTY3NWZkL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\", \"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":57},{\"Source\":\"Rotten Tomatoes\",\"Value\":45},{\"Source\":\"Metacritic\",\"Value\":50}], \"Metascore\":50, \"Imdb\" : { \"imdbRating\":5.7, \"imdbVotes\":187949, \"imdbID\":\"tt0133152\" }, \"BoxOffice\":178094583,\"Production\":\"20th Century Fox\", \"Website\":\"http://www.planetoftheapes.com\"}",
            "{\"Title\":\"The Social Network\",\"Year\":2010,\"Released\":{\"date\":\"2010-10-01\"},\"Runtime\":\"120 min\",\"Genre\":[\"Biography\", \"Drama\"], \"Directors\": [\"David Fincher\"],\"Writers\":[{\"Name\":\"Aaron Sorkin\", \"Contribution\":\"screenplay\"}, {\"Name\":\"Ben Mezrich\", \"Contribution\":\"book\"}],\"Actors\":[\"Jesse Eisenberg\", \"Rooney Mara\", \"Bryan Barter\", \"Dustin Fitzsimons\"],\"Plot\":\"Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, but is later sued by two brothers who claimed he stole their idea, and the co-founder who was later squeezed out of the business.\",\"Languages\":[\"English\", \"French\"],\"Country\":\"USA\",\"Awards\":\"Won 3 Oscars. Another 165 wins & 168 nominations.\",\"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BMTM2ODk0NDAwMF5BMl5BanBnXkFtZTcwNTM1MDc2Mw@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":77},{\"Source\":\"Rotten Tomatoes\",\"Value\":96},{\"Source\":\"Metacritic\",\"Value\":95}],\"Metascore\":95,\"Imdb\" : { \"imdbRating\":7.7,\"imdbVotes\":517235,\"imdbID\":\"tt1285016\"},\"BoxOffice\":96400000,\"Production\":\"Columbia Pictures\", \"Website\":\"http://www.thesocialnetwork-movie.com/\"}",
            "{\"Title\":\"A.I. Artificial Intelligence\",\"Year\":2001, \"Released\":{\"date\":\"2001-06-29\"},\"Runtime\":\"146 min\",\"Genre\":[\"Adventure\", \"Drama\", \"Sci-Fi\"],  \"Directors\": [\"Steven Spielberg\"], \"Writers\":[ {\"Name\":\"Brian Aldiss\", \"Contribution\":\"short story: Supertoys Last All Summer Long\"}, {\"Name\":\"Ian Watson\", \"Contribution\":\"screen story\"}, {\"Name\":\"Steven Spielberg\", \"Contribution\":\"screenplay\"}], \"Actors\":[\"Haley Joel Osment\", \"Frances O'Connor\", \"Sam Robards\", \"Jake Thomas\"],\"Plot\":\"A highly advanced robotic boy longs to become real so that he can regain the love of his human mother.\",\"Languages\":[\"English\"],\"Country\":\"USA\",\"Awards\":\"Nominated for 2 Oscars. Another 16 wins & 67 nominations.\",\"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BNWU2NzEyMDYtM2MyOS00OGM3LWFkNzAtMzRiNzE2ZjU5ZTljXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":71},{\"Source\":\"Rotten Tomatoes\",\"Value\":73},{\"Source\":\"Metacritic\",\"Value\":65}], \"Metascore\":65,\"Imdb\" : { \"imdbRating\":7.1,\"imdbVotes\":253222,\"imdbID\":\"tt0212720\"}, \"Production\":\"Dreamworks\",\"Website\":\"http://www.aimovie.com\"}",
            "{\"Title\":\"Blade Runner\",\"Year\":1982,\"Released\":{\"date\":\"1982-06-25\"},\"Runtime\":\"117 min\",\"Genre\":[\"Sci-Fi\", \"Thriller\"], \"Directors\":[\"Ridley Scott\"],\"Writers\":[{\"Name\":\"Hampton Fancher\", \"Contribution\":\"screenplay\"}, {\"Name\":\"David Webb Peoples\", \"Contribution\":\"screenplay\"}, {\"Name\":\"Philip K. Dick\", \"Contribution\":\"novel\"}],\"Actors\":[\"Harrison Ford\", \"Rutger Hauer\", \"Sean Young\", \"Edward James Olmos\"],\"Plot\":\"A blade runner must pursue and try to terminate four replicants who stole a ship in space and have returned to Earth to find their creator.\",\"Languages\":[\"English\", \"German\", \"Cantonese\", \"Japanese\", \"Hungarian\", \"Arabic\"],\"Country\":\"USA, Hong Kong, UK\",\"Awards\":\"Nominated for 2 Oscars. Another 11 wins & 16 nominations.\",\"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":82},{\"Source\":\"Rotten Tomatoes\",\"Value\":89},{\"Source\":\"Metacritic\",\"Value\":89}],\"Metascore\":89,\"Imdb\" : { \"imdbRating\":8.2,\"imdbVotes\":503684,\"imdbID\":\"tt0083658\"}, \"Production\":\"Warner Bros. Pictures\"}",
            "{\"Title\":\"2001: A Space Odyssey\",\"Year\":1968, \"Released\":{\"date\":\"1968-05-12\"},\"Runtime\":\"149 min\",\"Genre\":[\"Adventure\", \"Sci-Fi\"],\"Directors\":[\"Stanley Kubrick\"],\"Writers\":[ {\"Name\":\"Stanley Kubrick\", \"Contribution\":\"screenplay\"}, {\"Name\":\"Arthur C. Clarke\", \"Contribution\":\"screenplay\"}],\"Actors\":[\"Keir Dullea\", \"Gary Lockwood\", \"William Sylvester\", \"Daniel Richter\"],\"Plot\":\"Humanity finds a mysterious, obviously artificial object buried beneath the Lunar surface and, with the intelligent computer H.A.L. 9000, sets off on a quest.\",\"Languages\":[\"English\", \"Russian\"],\"Country\":\"UK, USA\",\"Awards\":\"Won 1 Oscar. Another 13 wins & 10 nominations.\",\"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BMTZkZTBhYmUtMTIzNy00YTViLTg1OWItNGUwMmVlN2FjZTVkXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":83},{\"Source\":\"Rotten Tomatoes\",\"Value\":94},{\"Source\":\"Metacritic\",\"Value\":86}],\"Metascore\":86,\"Imdb\" : {\"imdbRating\":8.3,\"imdbVotes\":469723,\"imdbID\":\"tt0062622\"}, \"BoxOffice\":135620, \"Production\":\"Warner Bros. Pictures\"}",
            "{\"Title\":\"WALL-E\",\"Year\":2008, \"Released\":{\"date\":\"2008-06-27\"},\"Runtime\":\"98 min\",\"Genre\":[\"Animation\", \"Adventure\", \"Family\"],\"Directors\":[\"Andrew Stanton\"],\"Writers\":[{\"Name\":\"Andrew Stanton\", \"Contribution\":\"original story by\"}, {\"Name\":\"Pete Docter\", \"Contribution\":\"original story by\"}, {\"Name\":\"Andrew Stanton\", \"Contribution\":\"screenplay\"}, {\"Name\":\"Jim Reardon\", \"Contribution\":\"screenplay\"}],\"Actors\":[\"Ben Burtt\", \"Elissa Knight\", \"Jeff Garlin\", \"Fred Willard\"],\"Plot\":\"In the distant future, a small waste-collecting robot inadvertently embarks on a space journey that will ultimately decide the fate of mankind.\",\"Languages\":[\"English\"],\"Country\":\"USA\",\"Awards\":\"Won 1 Oscar. Another 89 wins & 90 nominations.\",\"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BMjExMTg5OTU0NF5BMl5BanBnXkFtZTcwMjMxMzMzMw@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":84},{\"Source\":\"Rotten Tomatoes\",\"Value\":96},{\"Source\":\"Metacritic\",\"Value\":95}],\"Metascore\":95,\"Imdb\" : {\"imdbRating\":8.4,\"imdbVotes\":787663,\"imdbID\":\"tt0910970\"}, \"BoxOffice\":223749872,\"Production\":\"Walt Disney Pictures\",\"Website\":\"http://www.wall-e.com/\"}"
        ).map { converter.decodeFromString<Movie>(it) }

        val savedMovies = saveAll(movies)
        println("Saved movies: $savedMovies")
    }

    fun getMoviesReleasedSince2000() = with(repository) {
        val movies = getByQuery(
            value = "{ 'Released.date': { \$gte: '2000' } }",
            fields = "{ Title:1, Year:1, _id: 0 }"
        ).sortedBy {
            it.year
        }.map {
            it.title to it.year
        }

        println("Movies: $movies")
    }

    fun getMoviesWithImdbRatingGreaterThan75() = with(repository) {
        val movies = getByQuery(
            value = "{ 'Imdb.imdbRating': { \$gt: 7.5 } }",
            fields = "{ Title:1, Directors:1, Imdb: 1, _id: 0 }"
        ).map {
            setOf(it.title, it.directors, it.imdb)
        }

        println("Movies: $movies")
    }

    fun getMoviesInEnglishAndFrench() = with(repository) {
        val movies = getByQuery(
            value = "{ Languages: { \$in: [English, French] } }",
            fields = "{ Title:1, Country:1, Languages: 1, _id: 0 }"
        ).map {
            setOf(it.title, it.country, it.languages)
        }

        println("Movies: $movies")
    }

    fun getMoviesWhereStevenSpielbergScreenPlayed() = with(repository) {
        val movies = getByQuery(
            value = "{ 'Writers.Name': { \$eq: 'Steven Spielberg' }, 'Writers.Contribution': { \$eq: 'screenplay' }}",
            fields = "{ Title:1, Genre:1, _id: 0 }"
        ).map {
            it.title to it.genre
        }

        println("Movies: $movies")
    }

    fun getMoviesNoRated() = with(repository) {
        val movies = getByQuery(value = "{ Rated: { \$exists: false } }")
        println("Movies: $movies")
    }

    fun getMoviesWithRatingsAverage() = with(repository) {
        val movies = aggregateByCondition(
            condition = "{\$unwind: \"\$Ratings\" }, {\$group: { _id: \"\$Title\",averageRating: {\$avg: \"\$Ratings.Value\"}}}"
        )
        println("Movies: $movies")
    }
}