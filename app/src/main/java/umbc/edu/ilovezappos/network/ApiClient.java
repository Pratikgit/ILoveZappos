package umbc.edu.ilovezappos.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import umbc.edu.ilovezappos.utils.URLFactory;

/**
 * Created by Pratik on 25-01-2017.
 */

public class ApiClient {
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLFactory.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
