package com.example.starwars_nameslist;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.WindowManager;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
ArrayList personagens = new ArrayList() ;
RecyclerView lista;
RecyclerView.Adapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setContentView(R.layout.tela_splash);

       getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
       int i;
      for(i=1;i<=10;i++)
      {
         listar(i);
      }
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                setContentView(R.layout.activity_main);
               lista = (RecyclerView) findViewById(R.id.lista);
               lista.setAdapter(arrayAdapter(personagens));
               lista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        }, 5000);

    }
        public RecyclerView.Adapter arrayAdapter(ArrayList arraylist)
        {

            Adapter_Recycler adapter = new Adapter_Recycler(arraylist);
             System.out.println(arraylist.get(1).toString()+arraylist.get(2).toString()+ arraylist.get(3).toString());
          return adapter;
        }

        public void listar(int num){
            Call<Personagem> call = new RetrofitConfig().getSwapiI_interface().PegarDados(Integer.toString(num));

            call.enqueue(new Callback<Personagem>() {

                @Override
                public void onResponse(Call<Personagem> call, Response<Personagem> response) {

                    Personagem personagem = response.body();

                    personagens.add("Nome :" +personagem.getname()+"\n"+"Altura :"+personagem.getheight()+"\n"+"Ano de Nascimento :"+personagem.getbirth_year());

                }

                @Override
                public void onFailure(Call<Personagem> call, Throwable t) {
                    Log.e(null,t.getMessage());
                    System.out.println("Erro 1");
                }
            });
        }
}




