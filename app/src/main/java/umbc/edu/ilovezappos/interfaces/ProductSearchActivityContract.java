package umbc.edu.ilovezappos.interfaces;

import umbc.edu.ilovezappos.models.Product;

/**
 * Created by Pratik on 04-02-2017.
 */

public class ProductSearchActivityContract {
    public interface View {
        void onShareClick(Product product);

        void onItemClick(Product product);

        void onAddtoCartClick();
    }
}
