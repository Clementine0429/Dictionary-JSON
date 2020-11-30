package com.example.jsondict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Writer;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static LinkedList<Word> lst_word ;
    WordListAdapter adapter;
    EditText editSearch;
    Button btnSearch;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycleView);
        editSearch= findViewById(R.id.editSearch);
        btnSearch= findViewById(R.id.btnSearch);

        try {
            jsonString= new APIGetting(this).execute("1").get();

            if(get_lst_word(jsonString)){

                adapter= new WordListAdapter(lst_word, this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
            else{
                recyclerView.setVisibility(View.INVISIBLE);
                editSearch.setVisibility(View.INVISIBLE);
                btnSearch.setVisibility(View.INVISIBLE);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Boolean get_lst_word(String js){
        lst_word= new LinkedList<>();

        try {
            JSONArray jsonArray= new JSONArray(js);

            int num= jsonArray.length();
            for(int i=0; i<num; i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                Word word= new Word();

                word.setWord(jsonObject.getString("word"));
                word.setDefinition(jsonObject.getString("definition"));

                lst_word.add(word);
            }
            return  true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void Search(View view) {
        editSearch= findViewById(R.id.editSearch);
        String result= editSearch.getText().toString();

        LinkedList<Word> lst_search= new LinkedList<>();

        for(int i=0; i<lst_word.size(); i++){
            if(lst_word.get(i).getWord().toLowerCase().startsWith(result.toLowerCase())){
                lst_search.add(lst_word.get(i));
            }
        }

        if(lst_search.size()==0){
            Toast.makeText(this, "Cannot find the property result!", Toast.LENGTH_SHORT).show();

        }
        else{
            WordListAdapter adapter= new WordListAdapter(lst_search, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    public void Add(View view) {

        Intent intent= new Intent(this, ThemTuActivity.class);
        startActivity(intent);
    }
}