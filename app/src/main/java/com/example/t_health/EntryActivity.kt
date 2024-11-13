package com.example.t_health

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class EntryActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_entry)

        val mainLabel: TextView = findViewById(R.id.entryLabel)
        val userLogin: EditText = findViewById(R.id.userLoginEntry)
        val userPassword: EditText = findViewById(R.id.userPasswordEntry)
        val entryButton: Button = findViewById(R.id.entryButton)
        val regLink: TextView = findViewById(R.id.regLink)

        regLink.setOnClickListener {
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
        }

        entryButton.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()


            if (login == "" || password == "")
                Toast.makeText(this, "Не все поля заполнены!", Toast.LENGTH_LONG).show()
             else {

                val db = DataBHelper(this, null)
                val entryData = db.getUser(login, password)

                if(entryData){
                    Toast.makeText(this, " $login успешно авторизировался", Toast.LENGTH_LONG).show()


                    userLogin.text.clear()
                    userPassword.text.clear()

                } else
                    Toast.makeText(this, "Ошибка при входе в аккаунт", Toast.LENGTH_LONG).show()
            }
        }
    }
}



