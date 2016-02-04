package com.dtracker.core.service;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class GeocodeService {
    private final Geocoder geocoder;

    @Inject
    public GeocodeService(Geocoder geocoder) {
        this.geocoder = geocoder;
    }

    public String getAddressFromLocation(Location location) throws IOException {
        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        if (addresses == null || addresses.size() == 0) {
            return null;
        } else {
            Address address = addresses.get(0);
            String fullAddress = (!TextUtils.isEmpty(address.getThoroughfare()) ? (address.getThoroughfare() + ", ") : "") +
                    (!TextUtils.isEmpty(address.getSubThoroughfare()) ? (address.getSubThoroughfare() + ", ") : "") +
                    (!TextUtils.isEmpty(address.getLocality()) ? (address.getLocality() + ", ") : "") +
                    (!TextUtils.isEmpty(address.getCountryName()) ? (address.getCountryName() + ", ") : "");
            if (fullAddress.length() > 1) {
                fullAddress = fullAddress.substring(0, fullAddress.length() - 2);
            }
            return fullAddress;
        }
    }
}
