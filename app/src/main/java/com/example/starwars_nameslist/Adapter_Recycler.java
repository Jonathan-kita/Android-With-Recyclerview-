package com.example.starwars_nameslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

    public class Adapter_Recycler extends RecyclerView.Adapter<com.example.starwars_nameslist.Adapter_Recycler.ViewHolder>{

        private ArrayList nome;
        private ArrayList altura ;
        private ArrayList nascimento;



        public Adapter_Recycler(ArrayList NOME, ArrayList ALTURA,ArrayList NASCIMENTO)
        {
            nome = NOME;
            altura = ALTURA;
            nascimento = NASCIMENTO;

        }


        @Override
        public com.example.starwars_nameslist.Adapter_Recycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.personagem, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder( ViewHolder holder, int position) {

            holder.Nome.setText(nome.get(position).toString());
            holder.Altura.setText(altura.get(position).toString());
            holder.Nascimento.setText(nascimento.get(position).toString());

        }



        @Override
        public int getItemCount() {
            return  10;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {   TextView Nome,Altura,Nascimento;


            public ViewHolder(View item)
            {
                super(item);
                Nome = (TextView) item.findViewById(R.id.Nome);
                Altura = (TextView) item.findViewById(R.id.Altura);
                Nascimento = (TextView) item.findViewById(R.id.Nascimento);

            }
        }
    }


