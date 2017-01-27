package umbc.edu.ilovezappos.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import umbc.edu.ilovezappos.models.ProductsResponse;

/**
 * Created by Pratik on 26-01-2017.
 */

public interface ApiInterface {

        @GET("Search?")
        Call<ProductsResponse> getProductList(@Query("term") String term, @Query("key") String apiKey);
}
