package com.uplb.mlcsobrevinas.OPMrecommender;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uplb.mlcsobrevinas.OPMrecommender.Common.Common;
import com.uplb.mlcsobrevinas.OPMrecommender.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

//import java.util.Objects;

public class SignIn extends AppCompatActivity {
    EditText edtPhone, edtPassword;
    Button btnSignIn, privacyPolicy;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        btnSignIn = findViewById(R.id.btnSignIn);
        privacyPolicy = findViewById(R.id.privacyPolicy);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        Common.CategoryId="00";
/*chabngeees*/
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://igisobrevinas5.wixsite.com/website"));
                startActivity(intent);
            }
        });
//changeeeees
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(Common.isConnectedToInternet(getBaseContext())) {

                    final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                    mDialog.setMessage("Waiting...");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //check if user does not exist in database
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                //gets user information
                                mDialog.dismiss();
                                User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                                user.setPhone(edtPhone.getText().toString());
                                if (user != null) {
                                    if (user.getPassword().equals(edtPassword.getText().toString())) {
                                        Common.currentUser = user;
                                        Toast.makeText(SignIn.this, "Sign in succesful!", Toast.LENGTH_SHORT).show();
                                        if (Common.currentUser.getAgre() == 0 && Common.currentUser.getCons() == 0 && Common.currentUser.getExtro() == 0 && Common.currentUser.getNeuro() == 0 && Common.currentUser.getOpenn() == 0) {
                                            Intent intent = new Intent(SignIn.this, Start.class);
                                            startActivity(intent);
                                        }else {
                                            Intent intent = new Intent(SignIn.this, Home.class);
                                            startActivity(intent);
                                        }
                                    } else {
                                        Toast.makeText(SignIn.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(SignIn.this, "User does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Toast.makeText(SignIn.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }


}
