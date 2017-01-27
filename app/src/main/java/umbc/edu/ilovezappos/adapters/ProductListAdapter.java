package umbc.edu.ilovezappos.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import umbc.edu.ilovezappos.R;
import umbc.edu.ilovezappos.models.Product;
import umbc.edu.ilovezappos.utils.Util;

import static umbc.edu.ilovezappos.utils.Util.applyWhitneyBold;
import static umbc.edu.ilovezappos.utils.Util.applyWhitneyMedium;

/**
 * Created by Pratik on 27-01-2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private List<Product> mProductList;
    private Context context;
    private LayoutInflater mLayoutInflater;
    public ProductListAdapter(Context context, List<Product> productList){
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mProductList = productList;
    }
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_product_layout,parent,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.ProductViewHolder holder, int position) {
        Product currentProduct = mProductList.get(position);
        holder.brandNameTextView.setText(currentProduct.getBrandName());
        holder.prodNameTextView.setText(currentProduct.getProductName());
        holder.originalPriceTextView.setText(currentProduct.getOriginalPrice());
        holder.discountPriceTextView.setText(currentProduct.getPrice());
        holder.offTextView.setText(currentProduct.getPercentOff());
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView productImageView;
        TextView  brandNameTextView,originalPriceTextView,discountPriceTextView,offTextView,prodNameTextView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            brandNameTextView = (TextView) itemView.findViewById(R.id.text_brand_title);
            productImageView = (ImageView) itemView.findViewById(R.id.image_product);
            originalPriceTextView = (TextView) itemView.findViewById(R.id.text_product_originalprice);
            discountPriceTextView = (TextView) itemView.findViewById(R.id.text_product_discountprice);
            offTextView = (TextView) itemView.findViewById(R.id.text_product_discount_value);
            prodNameTextView = (TextView) itemView.findViewById(R.id.text_product_name);

            //setting Typeface for custom fonts.
            applyWhitneyMedium(brandNameTextView,context);
            applyWhitneyBold(originalPriceTextView,context);
            applyWhitneyMedium(discountPriceTextView,context);
            applyWhitneyMedium(offTextView,context);
            applyWhitneyMedium(prodNameTextView,context);

        }
    }


}
