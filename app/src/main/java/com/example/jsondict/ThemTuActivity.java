package com.example.jsondict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class ThemTuActivity extends AppCompatActivity {

    EditText editTu, editDinhNghia;

    private static String tu= null, dinhNghia=null;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tu);

        editTu= findViewById(R.id.editTuMoi);
        editDinhNghia= findViewById(R.id.editDinhNghiaMoi);
    }

    public void ThemTu(View view) {
        tu= editTu.getText().toString();
        dinhNghia= editDinhNghia.getText().toString();

        tu= tu.replaceAll(" ", "%20");
//        if(!kiemTraTuNhap(tu))
//            Toast.makeText(getApplicationContext(), "Từ nhập không đúng yêu cầu", Toast.LENGTH_SHORT).show();
//        else if(!kiemtraDinhNghiaNhap(dinhNghia))
//            Toast.makeText(getApplicationContext(), "Định nghĩa nhập không đúng yêu cầu", Toast.LENGTH_SHORT).show();
//        else
       // {
                Word word= new Word(tu, dinhNghia);
                try {
                     String str= new APIAdd(this).execute(word).get();
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    }
    public void Back(View view) {

        intent= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean kiemTraTuNhap(String tuNhap){
        if(tuNhap.contains(" ") || tuNhap.trim().length()==0)// nó do containt là kt ben trong co khoang trang ko? có thì false
            return false;
        return true;
    }
    public boolean kiemtraDinhNghiaNhap(String dinhNghia){
        int countSpace =0;
        for(int i=0; i < dinhNghia.trim().length(); i++)
            if(dinhNghia.charAt(i) == ' ')
                countSpace++;
        if(dinhNghia.trim().length()==0 || countSpace < 2)
            return false;
        return  true;
    }

}