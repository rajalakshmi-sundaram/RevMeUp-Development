package in.revmeup.revmeup;
import androidx.annotation.Keep;

@Keep
public class Advertisement {
    private String productName;
    private String productDescription;
    private String productImageUrl;
    public Advertisement(String productDescription,String productImageUrl,String productName )
    {
        this.productName=productName;
        this.productImageUrl=productImageUrl;
        this.productDescription=productDescription;
    }
    public Advertisement()
    {
    }
    @Override
    public String toString() {
        return "Advertisement{" +
                "product_Name='" + productName + '\'' +
                ", product_Image_URL='" + productImageUrl + '\'' +
                ", product_Description='" + productDescription +
                '}';
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
}
