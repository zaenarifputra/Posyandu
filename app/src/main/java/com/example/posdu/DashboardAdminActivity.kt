package com.example.posdu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.posdu.databinding.ActivityDashboardAdminBinding
import com.example.posdu.databinding.ActivityDashboardUserBinding
import com.google.firebase.auth.FirebaseAuth

class DashboardAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardAdminBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemReselectedListener{

            when(it.itemId){

                R.id.home -> replaceFragment(Home())
                R.id.notification -> replaceFragment(Notifications())
                R.id.profile -> replaceFragment(Profile())

                else -> {



                }
            }
            true

        }

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }


    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{
            val username = firebaseUser.email
            binding.subTitleTv.text = username
        }
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransactions = fragmentManager.beginTransaction()
        fragmentTransactions.replace(R.id.frame_layout, fragment)
        fragmentTransactions.commit()

    }
}