package umbc.edu.ilovezappos.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import umbc.edu.ilovezappos.BR;
import umbc.edu.ilovezappos.R;
import umbc.edu.ilovezappos.databinding.ItemProductLayoutBinding;
import umbc.edu.ilovezappos.interfaces.CallbackInterface;
import umbc.edu.ilovezappos.interfaces.ProductSearchActivityContract;
import umbc.edu.ilovezappos.presenters.ProductSearchActivityPresenter;
import umbc.edu.ilovezappos.models.Product;

import static umbc.edu.ilovezappos.utils.Util.applyWhitneyBold;
import static umbc.edu.ilovezappos.utils.Util.applyWhitneyMedium;

/**
 * Created by Pratik on 27-01-2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.BindingHolder> implements ProductSearchActivityContract.View {

    private List<Product> mProductList;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ProductSearchActivityPresenter presenter = new ProductSearchActivityPresenter(this);
    private CallbackInterface mCallback;

    public ProductListAdapter(Context context, List<Product> productList, CallbackInterface mCallback) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mProductList = productList;
        this.mCallback = mCallback;
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        private ItemProductLayoutBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
            binding.setPresenter(presenter);
        }

        public ItemProductLayoutBinding getBinding() {
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

    @Override
    public void onShareClick(Product product) {
        mCallback.onRecyclerShareClick(product);
    }

    @Override
    public void onItemClick(Product product) {
        mCallback.onRecyclerItemClick(product);
    }

    @Override
    public void onAddtoCartClick() {
        mCallback.onAddToCartClick();
    }

}
