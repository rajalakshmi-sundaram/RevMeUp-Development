package in.revmeup.revmeup;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Keep
public class Product {
    private String product_id;
    private String product_name;
    private String amazon_id;
    private String price;
    private String product_link_amazon;
    private String subcategory;
    private String brand_name;
    private String category;
    private String description;
    private List<String> photos;
    private ArrayList<Store> stores;
    public Product(String product_id, String product_name, String amazon_id, String category, String description, List<String> photos) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.amazon_id = amazon_id;
        this.category = category;
        this.description = description;
        this.photos = photos;
    }
    public Product(){
    }
    @Override
    public String toString() {
        return "Product{" +
                "product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", amazon_id='" + amazon_id + '\'' +
                ", price='" + price + '\'' +
                ", product_link_amazon='" + product_link_amazon + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", photos=" + photos +
                ", stores=" + stores +
                '}';
    }
    public String getProduct_link_amazon() {
        return product_link_amazon;
    }
    public void setProduct_link_amazon(String product_link_amazon) {
        this.product_link_amazon = product_link_amazon;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getAmazon_id() {
        return amazon_id;
    }

    public void setAmazon_id(String amazon_id) {
        this.amazon_id = amazon_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Product product = (Product) obj;
        return product_id.matches(product.getProduct_id());
    }

    public static class Store{
        private String storeProductId;
        private String storeLink;
        private String storeName;
        private String storePrice;

        public Store(){}

        public Store(String storeProductId, String storeLink, String storeName, String storePrice) {
            this.storeProductId = storeProductId;
            this.storeLink = storeLink;
            this.storeName = storeName;
            this.storePrice = storePrice;
        }

        @Override
        public String toString() {
            return "Store{" +
                    "storeProductId='" + storeProductId + '\'' +
                    ", storeLink='" + storeLink + '\'' +
                    ", storeName='" + storeName + '\'' +
                    ", storePrice='" + storePrice + '\'' +
                    '}';
        }

        public String getStoreProductId() {
            return storeProductId;
        }

        public void setStoreProductId(String storeProductId) {
            this.storeProductId = storeProductId;
        }

        public String getStoreLink() {
            return storeLink;
        }

        public void setStoreLink(String storeLink) {
            this.storeLink = storeLink;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStorePrice() {
            return storePrice;
        }

        public void setStorePrice(String storePrice) {
            this.storePrice = storePrice;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            Store store = (Store) obj;
            return storeProductId.matches(store.getStoreProductId());
        }
    }
}