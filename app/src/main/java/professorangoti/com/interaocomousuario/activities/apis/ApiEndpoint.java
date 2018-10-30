package professorangoti.com.interaocomousuario.activities.apis;

import java.util.List;

import professorangoti.com.interaocomousuario.activities.domain.Produto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoint {

    @GET("produtos/{id}")
    Call<List<Produto>> obterProduto();
}
