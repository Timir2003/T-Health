package com.example.t_health

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_entry)

        val regLabel: TextView = findViewById (R.id.regLabel)
        val userLogin: EditText = findViewById (R.id.userLogin)
        val userEmail: EditText = findViewById(R.id.userEmail)
        val userPassword: EditText = findViewById(R.id.userPassword)
        val userPasswordConfirm: EditText = findViewById(R.id.userPasswordConfirm)
        val checkboxAgreement: CheckBox = findViewById(R.id.userAgreement)
        val regButton: Button = findViewById(R.id.regButton)
        val entrylink: TextView = findViewById(R.id.entryLink)


        entrylink.setOnClickListener {
            val intent = Intent (this, EntryActivity::class.java)
            startActivity(intent)
        }


        regButton.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val confpassword = userPasswordConfirm.text.toString().trim()

            if (login == "" || password == "" || confpassword == "")
                Toast.makeText(this, "Не все поля заполнены!", Toast.LENGTH_LONG).show()
            if (confpassword != password || password != confpassword) {
                Toast.makeText(this, "Пароли не совпадают!", Toast.LENGTH_LONG).show()
                userPasswordConfirm.text.clear()
            }
            else {
                val user = User(login, password, email)

                val db = DataBHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Аккаунт $login зарегистрирован", Toast.LENGTH_LONG).show()

                userLogin.text.clear()
                userPassword.text.clear()
                userEmail.text.clear()
                userPasswordConfirm.text.clear()
            }
        }


    }
}