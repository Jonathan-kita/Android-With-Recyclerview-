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
ArrayList altura = new ArrayList() ;
ArrayList nascimento = new ArrayList() ;
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
                lista.setAdapter(arrayAdapter(personagens,altura,nascimento));
                lista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        }, 5000);

    }
        public RecyclerView.Adapter arrayAdapter(ArrayList Personagens,ArrayList Altura,ArrayList Nascimento)
        {
            Adapter_Recycler adp = new Adapter_Recycler(Personagens,Altura,Nascimento);
         //   System.out.println(personagens.get(1).toString()+personagens.get(2).toString()+personagens.get(3).toString());

          return adp;
        }

        public void listar(int num){
            Call<Personagem> call = new RetrofitConfig().getSwapiI_interface().PegarDados(Integer.toString(num));

            call.enqueue(new Callback<Personagem>() {

                @Override
                public void onResponse(Call<Personagem> call, Response<Personagem> response) {

                    Personagem personagem = response.body();

                    personagens.add("Nome :" +personagem.getname());
                    altura.add("Altura :"+personagem.getheight());
                    nascimento.add("Ano de Nascimento :"+personagem.getbirth_year());
                }

                @Override
                public void onFailure(Call<Personagem> call, Throwable t) {
                    Log.e(null,t.getMessage());
                    System.out.println("Erro 1");
                }
            });
        }
}




