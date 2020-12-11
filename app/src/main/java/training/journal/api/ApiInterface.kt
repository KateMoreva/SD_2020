package training.journal.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import training.journal.api.responses.MessageResponse
import training.journal.dto.UserSignUpDto

/**
 * Your Request-methods should be here
 */
interface ApiInterface {

    /**
     * @GET("path/path...")
     * fun getUserTrainings(userId: String): Single<TrainingDto>
     */

    @Headers("Content-Type: application/json")
    @POST("/users/create")
    fun createUserRequest(@Body userSignUpDto: UserSignUpDto): Single<Response<MessageResponse>>

    @GET("/users/login")
    fun loginRequest(@Header("Authorization") token: String): Single<Response<MessageResponse>>
}