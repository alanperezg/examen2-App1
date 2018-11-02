package com.iteso.pdm18_scrollabletabs.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {
    private	int	id;
    private	String	name;
    private	String	phone;
    private	int	thumbnail;
    private	double	latitude;
    private	double	longitude;
    private	City	city;

    public Store(){};
    protected Store(Parcel in) {
        id = in.readInt();
        name = in.readString();
        phone = in.readString();
        thumbnail = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        city = in.readParcelable(City.class.getClassLoader());
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public City getCity() {
        return city;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeInt(thumbnail);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeParcelable(city, i);
    }
}
