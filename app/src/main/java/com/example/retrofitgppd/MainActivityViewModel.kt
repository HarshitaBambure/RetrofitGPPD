package com.example.retrofitgppd
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitgppd.retrofit.ApiInterface
import com.example.retrofitgppd.retrofit.RetrofitClient
import com.example.retrofitgppd.retrofit.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.retrofitgppd.retrofit.UserResponse

class MainActivityViewModel: ViewModel() {
    lateinit var createNewUserLiveData: MutableLiveData<UserResponse?>
    init {
        createNewUserLiveData = MutableLiveData()
    }

    fun getCreateNewUserObserver(): MutableLiveData<UserResponse?> {
        return createNewUserLiveData
    }


    fun createNewUser(user: User) {
        val retroService  = RetrofitClient.getRetroInstance().create(ApiInterface::class.java)
        val call = retroService.createUser(user)
        call.enqueue(object: Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }
        })
    }


}