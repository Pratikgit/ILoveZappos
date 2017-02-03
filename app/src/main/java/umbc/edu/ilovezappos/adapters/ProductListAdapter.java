package umbc.edu.ilovezappos.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import umbc.edu.ilovezappos.BR;
import umbc.edu.ilovezappos.R;
import umbc.edu.ilovezappos.models.Product;
import umbc.edu.ilovezappos.utils.Util;

import static umbc.edu.ilovezappos.utils.Util.applyWhitneyBold;
import static umbc.edu.ilovezappos.utils.Util.applyWhitneyMedium;

/**
 * Created by Pratik on 27-01-2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.BindingHolder> {

    private List<Product> mProductList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public ProductListAdapter(Context context, List<Product> productList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mProductList = productList;
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_product_layout, parent, false);
        BindingHolder productBindingHolder = new BindingHolder(view);
        return productBindingHolder;
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.BindingHolder holder, int position) {
        Product currentProduct = mProductList.get(position);
        holder.setIsRecyclable(false);
        holder.getBinding().setVariable(BR.product, currentProduct);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}
