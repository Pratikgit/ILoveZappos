package umbc.edu.ilovezappos.interfaces;

import android.content.Context;

import umbc.edu.ilovezappos.models.Product;

/**
 * Created by Pratik on 04-02-2017.
 */

public interface CallbackInterface {
    public void onRecyclerItemClick(Product product);

    public void onRecyclerShareClick(Product product);

    public void onAddToCartClick();
}
