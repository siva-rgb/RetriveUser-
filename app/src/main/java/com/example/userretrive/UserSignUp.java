package com.example.userretrive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserSignUp extends AppCompatActivity {

    EditText signUp_name,signUp_password,signUp_email,signUp_Number;
    Button sign_Up;
    FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        signUp_name=findViewById(R.id.signUp_name);
        signUp_password=findViewById(R.id.signUp_password);
        signUp_Number=findViewById(R.id.signUp_number);
        signUp_email=findViewById(R.id.signUp_Email);
        sign_Up=findViewById(R.id.btn_SignUp);
        mAuth=FirebaseAuth.getInstance();

        sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_Name=signUp_name.getText().toString();
                String user_Password=signUp_password.getText().toString();
                String user_Number=signUp_Number.getText().toString();
                String user_Email=signUp_email.getText().toString();

                if (TextUtils.isEmpty(user_Email) || TextUtils.isEmpty(user_Name) || TextUtils.isEmpty(user_Number)|| TextUtils.isEmpty(user_Password))
                {
                    Toast.makeText(UserSignUp.this,"All fields are need to be filled",Toast.LENGTH_SHORT).show();
                }
                else if (user_Password.length()<6)
                {

                    Toast.makeText(UserSignUp.this,"password is too short",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    signUp(user_Name,user_Email,user_Number,user_Password);
                }
            }
        });
    }

    private void signUp(final String username,final String useremail,final String usernumber,final String userpassword)
    {
        mAuth.createUserWithEmailAndPassword(username,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser fUser = mAuth.getCurrentUser();
                    assert fUser != null;
                    String userId = fUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference("User").child(userId);

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", userId);
                    hashMap.put("username", username);
                    hashMap.put("usernumber", usernumber);
                    hashMap.put("userpassword",userpassword);
                    hashMap.put("useremail", useremail);
                    hashMap.put("imageURL", "default");

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Intent intent=new Intent(UserSignUp.this,ProfileActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(UserSignUp.this, "You cannot Sign up with this username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
