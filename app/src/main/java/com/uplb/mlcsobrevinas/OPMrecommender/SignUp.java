package com.uplb.mlcsobrevinas.OPMrecommender;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uplb.mlcsobrevinas.OPMrecommender.Common.Common;
import com.uplb.mlcsobrevinas.OPMrecommender.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPhone, edtName, edtPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = (MaterialEditText) findViewById(R.id.edtName);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Common.isConnectedToInternet(getBaseContext())) {
                    final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                    mDialog.setMessage("Waiting...");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                mDialog.dismiss();
                                Toast.makeText(SignUp.this, "Phone number already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                mDialog.dismiss();
                                //if error occured remove edtPhone.getText().toString() and remove Phone in sign up
                                User user = new User(edtName.getText().toString(), edtPassword.getText().toString(), 0, 0, 0, 0, 0);
                                table_user.child(edtPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Sign Up Success!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                } else {
                    Toast.makeText(SignUp.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}


