package umbc.edu.ilovezappos.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umbc.edu.ilovezappos.R;
import umbc.edu.ilovezappos.adapters.ProductListAdapter;
import umbc.edu.ilovezappos.interfaces.ApiInterface;
import umbc.edu.ilovezappos.models.Product;
import umbc.edu.ilovezappos.models.ProductsResponse;
import umbc.edu.ilovezappos.network.ApiClient;
import umbc.edu.ilovezappos.utils.Constants;
import umbc.edu.ilovezappos.utils.Util;

import static umbc.edu.ilovezappos.utils.Util.applyWhitneyMedium;

public class ProductSearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mtoolbar;
    private FloatingActionButton mCartFab;
    private Context mcontext;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private ApiInterface apiService;
    private CoordinatorLayout coordinatorLayout;
    private SearchView msearchView;
    private EditText mSearchBox;
    private String mSearchQuery;
    private RecyclerView mRecyclerView;
    private ProductListAdapter mProductListAdapter;
    private final int GRID_SPAN_COUNT =2;

    private static final String TAG = ProductSearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        mcontext = this;
        setUpToolbar();
        setUpUIElements();
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    private void getProductsAPICall() {
        Call<ProductsResponse> call = apiService.getProductList(mSearchQuery, Constants.API_KEY);
        Log.i(TAG+ "Url -> ",call.request().url().toString());
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                int statusCode = response.code();
                if(statusCode == 200) {
                    List<Product> products = response.body().getResults();
                    displayProductList(products);
                    // recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                }else {
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
                // Log error here since request failed
                Log.e(TAG, t.toString());

            }
        });
    }

    /**
     * This function displays the list of products fetched in recyler view.
     * @param productList
     */
    private void displayProductList(List<Product> productList) {
        mProductListAdapter = new ProductListAdapter(mcontext,productList);
        mRecyclerView.setAdapter(mProductListAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mcontext,GRID_SPAN_COUNT));

    }

    private void setUpUIElements() {
        mCartFab = (FloatingActionButton) findViewById(R.id.fab);
        mCartFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mtoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cordinator_layout);
        msearchView = (SearchView) findViewById(R.id.searchView);
        msearchView.setQueryHint(getResources().getString(R.string.search_title_hint));
        msearchView.onActionViewExpanded();
        msearchView.setIconified(false);
        mSearchBox =((EditText) msearchView.findViewById (android.support.v7.appcompat.R.id.search_src_text));
        applyWhitneyMedium(mSearchBox,mcontext);

        // adding QueryListner
        msearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                mSearchQuery = s.trim();
                if(Util.isNetworkConnected(mcontext))
                    getProductsAPICall();
                else{
                    // Snackbar to show connectivity error
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(R.string.error_message_connection_error), Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    View view = snackbar.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER);
                    snackbar.show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recylerView);

    }


    private void setUpToolbar() {
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_search) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
