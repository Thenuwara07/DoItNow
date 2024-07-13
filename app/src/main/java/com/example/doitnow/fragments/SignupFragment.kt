package com.example.doitnow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.doitnow.R
import com.example.doitnow.databinding.FragmentSignupBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment() {

    private lateinit var auth:FirebaseAuth
    private lateinit var navControl:NavController
    private lateinit var binding:FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }

    private fun init(view:View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    private fun registerEvents(){

        binding.authTextView.setOnClickListener {
            navControl.navigate(R.id.action_signupFragment_to_loginFragment)
        }
        binding.nxtbtn.setOnClickListener {
            val email = binding.useremail.text.toString()
            val pass = binding.userpassword.text.toString()
            val repass = binding.userrepassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && repass.isNotEmpty()){
                if (pass == repass){
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(
                        OnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText( context, "Registered Successfully", Toast.LENGTH_SHORT).show()
                                navControl.navigate(R.id.action_signupFragment_to_homeFragment)
                            }else{
                                Toast.makeText( context, it.exception?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }

        }
    }

}