package com.address.pickview;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.addresspicker.AddressPickerView;
import com.addresspicker.model.AddressBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AddressPickerView addressPickerView;
    private List<AddressBean> addressBeanList;
    private TextView mAddressTextView;
    private int[] i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddressTextView = (TextView) this.findViewById(R.id.textView_address);

        this.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressPickerView.setSelect(i);
                addressPickerView.show();
            }
        });

        addressBeanList = new Gson().fromJson(getAddressData(), new TypeToken<List<AddressBean>>() {}.getType());

        addressPickerView = new AddressPickerView(this, R.style.Dialog, addressBeanList);

        addressPickerView.setAreaPickerViewCallback(new AddressPickerView.AreaPickerViewCallback() {

            @Override
            public void callback(int... value) {
                i = value;
                if (value.length == 3) {
                    mAddressTextView.setText(addressBeanList.get(value[0]).getLabel() + "-" + addressBeanList.get(value[0]).getChildren().get(value[1]).getLabel() + "-" + addressBeanList.get(value[0]).getChildren().get(value[1]).getChildren().get(value[2]).getLabel());
                }else{
                    mAddressTextView.setText(addressBeanList.get(value[0]).getLabel() + "-" + addressBeanList.get(value[0]).getChildren().get(value[1]).getLabel());
                }
            }
        });
    }

    private String getAddressData() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = this.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open("address.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
