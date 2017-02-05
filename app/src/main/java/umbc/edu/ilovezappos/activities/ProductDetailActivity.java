package umbc.edu.ilovezappos.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umbc.edu.ilovezappos.R;
import umbc.edu.ilovezappos.databinding.ActivityProductDetailsBinding;
import umbc.edu.ilovezappos.interfaces.ApiInterface;
import umbc.edu.ilovezappos.models.Product;
import umbc.edu.ilovezappos.models.ProductsResponse;
import umbc.edu.ilovezappos.network.ApiClient;
import umbc.edu.ilovezappos.utils.Constants;
import umbc.edu.ilovezappos.utils.CustomProgressBarDialog;

/**
 * Created by Pratik on 04-02-2017.
 */

public class ProductDetailActivity extends AppCompatActivity {
    private Toolbar mtoolbar;
    private Product mProductdetail;
    String query;
    String[] queryData;
    private ActivityProductDetailsBinding activityProductDetailsBinding;
    private CustomProgressBarDialog mprogressBar;
    private static final String TAG = ProductDetailActivity.class.getSimpleName();
    private ApiInterface apiService;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent intent = getIntent();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        setUpUI();
        if (intent != null) {
            if (intent.getAction() == Intent.ACTION_DEFAULT) {
                // from external URI.
                query = intent.getData().toString();
                if (!query.isEmpty()) {
                    queryData = new String[3];
                    queryData = query.split("\\&");
                    getProductsAPICall();
                }
            } else {
                // normal flow
                mProductdetail = intent.getParcelableExtra("product");
                datBindingsetupUI(mProductdetail);
                setUpToolbar();
            }
        }
    }

    private void setUpUI() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cordinator_layout);
        mprogressBar = new CustomProgressBarDialog(this);
        mprogressBar.setContentView(R.layout.progressbar_custom_layout);
        mprogressBar.setCancelable(false);
    }

    /**
     * Binds the layout
     *
     * @param mProductdetail - data object to be binded
     */
    private void datBindingsetupUI(Product mProductdetail) {

        activityProductDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);
        activityProductDetailsBinding.setProduct(mProductdetail);
    }

    /**
     * Sets up the toolbar with the provided binder
     */
    private void setUpToolbar() {
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(activityProductDetailsBinding.toolbar);
        getSupportActionBar().setTitle(mProductdetail.getBrandName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getProductsAPICall() {
        showProgressDialog();
        Call<ProductsResponse> call = apiService.getProductList(queryData[1], Constants.API_KEY);
        Log.i(TAG + "Url -> ", call.request().url().toString());
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                hideProgressDialog();
                int statusCode = response.code();
                if (statusCode == 200) {
                    List<Product> products = response.body().getResults();
                    for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).getProductId().equalsIgnoreCase(queryData[2])) {
                            mProductdetail = products.get(i);
                            break;
                        }
                    }
                    if (mProductdetail != null) {
                        datBindingsetupUI(mProductdetail);
                        setUpToolbar();
                    }
                } else {
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(R.string.error_message_failure), Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    View view = snackbar.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                hideProgressDialog();
                // Log error here since request failed
                Log.e(TAG, t.toString());

            }
        });
    }

    /**
     * Show the progress dialog
     */
    private void showProgressDialog() {
        if (!mprogressBar.isShowing())
            mprogressBar.show();
    }

    /**
     * Hides the progress dialog if shown
     */
    private void hideProgressDialog() {
        if (mprogressBar.isShowing())
            mprogressBar.dismiss();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
