package org.team.rentwheels.models;

public class Brand {
    private int id;
    private String brandName;
    private String countyOfMake;
    private int foundationYear;

    private byte[] image;


    public Brand() {
    }

    public Brand(int id,
                 String brandName,
                 String countyOfMake,
                 int foundationYear,
                 byte[] image) {
        this.id = id;
        this.brandName = brandName;
        this.countyOfMake = countyOfMake;
        this.foundationYear = foundationYear;
        this.image = image;
    }

    public Brand(String brandName,
                 String countyOfMake,
                 int foundationYear,
                 byte[] image) {
        this.brandName = brandName;
        this.countyOfMake = countyOfMake;
        this.foundationYear = foundationYear;
        this.image = image;
    }

    public Brand(String brandName,
                 String countyOfMake,
                 int foundationYear
                 ) {
        this.brandName = brandName;
        this.countyOfMake = countyOfMake;
        this.foundationYear = foundationYear;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCountyOfMake() {
        return countyOfMake;
    }

    public void setCountyOfMake(String countyOfMake) {
        this.countyOfMake = countyOfMake;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", countyOfMake='" + countyOfMake + '\'' +
                ", foundationYear=" + foundationYear +
                '}';
    }
}
