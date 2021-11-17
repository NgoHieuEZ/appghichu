package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button btnDangKy;
    EditText Edusername,Edpassword,EdpasswordAgain;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();

        myDB=new DBHelper(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=Edusername.getText().toString();
                String pass=Edpassword.getText().toString();
                String repass=EdpasswordAgain.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(RegisterActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean userheckResult= myDB.checkusername(user);
                        if(userheckResult==false){
                           Boolean regResult= myDB.insertData(user,pass);

                           if(regResult==true){
                               Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                               startActivity(intent);

                           }else{
                               Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                           }

                        }else{
                            Toast.makeText(RegisterActivity.this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public void AnhXa(){
        btnDangKy=(Button) findViewById(R.id.btnLogUp1);
        Edusername=(EditText) findViewById(R.id.inputEmail1);
        Edpassword=(EditText) findViewById(R.id.inputPassword1);
        EdpasswordAgain=(EditText) findViewById(R.id.inputPasswordAgain1);

    }
}