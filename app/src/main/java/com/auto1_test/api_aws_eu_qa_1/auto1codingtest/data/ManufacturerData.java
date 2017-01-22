package com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data;

import java.io.Serializable;

/**
 * Created by Developer on 1/21/2017.
 */

public class ManufacturerData implements Serializable {

    public ManufacturerData(String mManufacturerId, String mManufacturerName) {
        this.mManufacturerId = mManufacturerId;
        this.mManufacturerName = mManufacturerName;
    }

    public String getmManufacturerId() {
        return mManufacturerId;
    }

    public String getmManufacturerName() {
        return mManufacturerName;
    }


    public String mManufacturerId;
    public String mManufacturerName;
}
