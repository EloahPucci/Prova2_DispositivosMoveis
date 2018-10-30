package professorangoti.com.interaocomousuario.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import professorangoti.com.interaocomousuario.R;
import professorangoti.com.interaocomousuario.activities.apis.ApiEndpoint;
import professorangoti.com.interaocomousuario.activities.domain.Produto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FinishActivity extends AppCompatActivity {

    private String pedido;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Intent intent = getIntent();
        pedido = intent.getStringExtra("pedido");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://provaddm2018.atwebpages.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        ApiEndpoint apiService = retrofit.create(ApiEndpoint.class);
        Call<List<Produto>> call = apiService.obterProduto();

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                int statusCode = response.code();
                List<Produto> produtos = response.body();

                textView=(TextView)findViewById(R.id.valor_do_pedido);

                for(Produto aux: produtos){

                    if(aux.getProduto().equals(pedido)){
                        textView.setText("R$" + aux.getValor() + ".00");

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {

            }
        });
    }
}
