package umbc.edu.ilovezappos.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import umbc.edu.ilovezappos.R;
import umbc.edu.ilovezappos.databinding.ActivityProductDetailsBinding;
import umbc.edu.ilovezappos.models.Product;

/**
 * Created by Pratik on 04-02-2017.
 */

public class ProductDetailActivity extends AppCompatActivity {
    private Toolbar mtoolbar;
    private Product mProductdetail;
    private ActivityProductDetailsBinding activityProductDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent intent = getIntent();
        if (intent != null) {
            mProductdetail = intent.getParcelableExtra("product");
            datBindingsetupUI(mProductdetail);
            setUpToolbar();
        }
    }

    /**
     * Binds the layout
     * @param mProductdetail - data object to be binded
     */
    private void datBindingsetupUI(Product mProductdetail) {

        activityProductDetailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_product_details);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
