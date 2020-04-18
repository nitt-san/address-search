package entity;

/**
 * 住所情報エンティティ
 */
public class Address {

    // 住所情報ID
    private Integer addressId = 0;
    // 郵便番号
    private String postCode = "";
    // 都道府県
    private String prefecture = "";
    // 市区町村
    private String city = "";
    // その他住所
    private String townArea = "";

    // コンストラクタ
    private Address() {
    }

    // Factory
    public static Address createAddressIndex() {
        return new Address();
    }

    // 以下はSetter/Getter
    public Integer getAddressId() { return addressId; }

    public void setAddressId(Integer addressId) { this.addressId = addressId;}

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTownArea() {
        return townArea;
    }

    public void setTownArea(String townArea) {
        this.townArea = townArea;
    }

}

