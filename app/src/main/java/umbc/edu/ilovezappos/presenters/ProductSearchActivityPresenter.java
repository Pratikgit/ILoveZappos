package umbc.edu.ilovezappos.presenters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import umbc.edu.ilovezappos.activities.ProductSearchActivity;
import umbc.edu.ilovezappos.interfaces.ProductSearchActivityContract;
import umbc.edu.ilovezappos.models.Product;

/**
 * Created by Pratik on 03-02-2017.
 */

public class ProductSearchActivityPresenter {
    private ProductSearchActivityContract.View view;

    public ProductSearchActivityPresenter(ProductSearchActivityContract.View view) {
        this.view = view;
    }

    public void onShareClick(Product product) {
        view.onShareClick(product);
    }

    public void onItemClick(Product product) {
        view.onItemClick(product);
    }

    public void onAddToCartClick() {
        view.onAddtoCartClick();
    }

}
