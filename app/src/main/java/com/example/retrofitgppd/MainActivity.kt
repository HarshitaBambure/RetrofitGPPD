package com.example.retrofitgppd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitgppd.databinding.ActivityMainBinding
import com.example.retrofitgppd.retrofit.User
import com.example.retrofitgppd.retrofit.UserResponse

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViewModel()
       binding.buttonCreate.setOnClickListener {
            createUser()
        }
    }
    private fun createUser() {
        val user  = User("", binding.editTextName.text.toString(), binding.editTextEmail.text.toString(), "Active", "Male")
        viewModel.createNewUser(user)

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getCreateNewUserObserver().observe(this, Observer <UserResponse?>{

            if(it  == null) {
                Toast.makeText(this@MainActivity, "Failed to create User", Toast.LENGTH_LONG).show()
            } else {
                //{"code":201,"meta":null,"data":{"id":2877,"name":"xxxxxaaaaabbbbb","email":"xxxxxaaaaabbbbb@gmail.com","gender":"male","status":"active"}}
                Toast.makeText(this@MainActivity, "Successfully created User", Toast.LENGTH_LONG).show()
            }
        })
    }


}