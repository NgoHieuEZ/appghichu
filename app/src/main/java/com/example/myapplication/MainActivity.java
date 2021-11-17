package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDangNhap,btnDangKy;
    EditText Edusername,Edpassword;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);

            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=Edusername.getText().toString();
                String password=Edpassword.getText().toString();

                if(user.equals("")||password.equals("")){
                    Toast.makeText(MainActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean userpassCheckResult= myDB.checkusernamepassword(user,password);
                    if(userpassCheckResult==true){
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public void AnhXa(){
        btnDangKy=(Button) findViewById(R.id.btnLogUp);
        btnDangNhap=(Button) findViewById(R.id.btnLogin);
        Edusername=(EditText) findViewById(R.id.inputEmail);
        Edpassword=(EditText) findViewById(R.id.inputPassword);
        myDB=new DBHelper(this);
    }
}