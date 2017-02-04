package umbc.edu.ilovezappos.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pratik on 25-01-2017.
 */

public class Product implements Parcelable {
    private String brandName;
    private String thumbnailImageUrl;
    private String productId;
    private String originalPrice;
    private String styleId;
    private String colorId;
    private String price;
    private String percentOff;
    private String productUrl;
    private String productName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected Product(Parcel in) {
        brandName = in.readString();
        thumbnailImageUrl = in.readString();
        productId = in.readString();
        originalPrice = in.readString();
        styleId = in.readString();
        colorId = in.readString();
        price = in.readString();
        percentOff = in.readString();
        productUrl = in.readString();
        productName = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(brandName);
        parcel.writeString(thumbnailImageUrl);
        parcel.writeString(productId);
        parcel.writeString(originalPrice);
        parcel.writeString(styleId);
        parcel.writeString(colorId);
        parcel.writeString(price);
        parcel.writeString(percentOff);
        parcel.writeString(productUrl);
        parcel.writeString(productName);
    }
}
