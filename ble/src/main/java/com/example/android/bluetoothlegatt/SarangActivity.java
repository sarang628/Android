package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SarangActivity extends Activity
{

    private final static String TAG = DeviceControlActivity.class.getSimpleName();
    private String mDeviceName;
    private String mDeviceAddress;
    private boolean mConnected = false;
    private BluetoothLeService mBluetoothLeService;


    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

    private final int layoutIds[] = {
            R.id.line1,
            R.id.line2,
            R.id.line3,
            R.id.line4,
            R.id.line5,
            R.id.line6,
            R.id.line7,
            R.id.line8,
            R.id.line9,
            R.id.line10,
            R.id.line11,
            R.id.line12,
            R.id.line13,
            R.id.line14,
            R.id.line15,
            R.id.line16,
            R.id.line17,
            R.id.line18,
            R.id.line19,
            R.id.line20,
            R.id.line21,
            R.id.line22,
            R.id.line23,
            R.id.line24,
            R.id.line25,
            R.id.line26,
            R.id.line27,
            R.id.line28,
            R.id.line29,
            R.id.line30,
            R.id.line31,
            R.id.line32,
    };

    private final int itemids[] = {
            R.id.item1,
            R.id.item2,
            R.id.item3,
            R.id.item4,
            R.id.item5,
            R.id.item6,
            R.id.item7,
            R.id.item8,
            R.id.item9,
            R.id.item10,
            R.id.item11,
            R.id.item12,
            R.id.item13,
            R.id.item14,
            R.id.item15,
            R.id.item16,
            R.id.item17,
            R.id.item18,
            R.id.item19,
            R.id.item20,
            R.id.item21,
            R.id.item22,
            R.id.item23,
            R.id.item24,
            R.id.item25,
            R.id.item26,
            R.id.item27,
            R.id.item28,
            R.id.item29,
            R.id.item30,
            R.id.item31,
            R.id.item32,
            R.id.item33,
            R.id.item34,
            R.id.item35,
            R.id.item36,
            R.id.item37,
            R.id.item38,
            R.id.item39,
            R.id.item40,
            R.id.item41,
            R.id.item42,
            R.id.item43,
            R.id.item44,
            R.id.item45,
            R.id.item46,
            R.id.item47,
            R.id.item48,
            R.id.item49,
            R.id.item50,
            R.id.item51,
            R.id.item52,
            R.id.item53,
            R.id.item54,
            R.id.item55,
            R.id.item56,
            R.id.item57,
            R.id.item58,
            R.id.item59,
            R.id.item60,
            R.id.item61,
            R.id.item62,
            R.id.item63,
            R.id.item64
    };

    boolean drawCheck = true;

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            Log.d(TAG, "onCheckedChanged" + buttonView.getTag());
            Log.d(TAG, "onCheckedChanged" + buttonView.getX() + "," + buttonView.getTop() + "," + buttonView.getBottom());
            int value = 0;

            Log.d("sarang", "" + arrCheckBox[0][0].getX());
            Log.d("sarang", "" + arrCheckBox[0][0].getY());
            Log.d("sarang", "" + arrCheckBox[1][1].getX());
            Log.d("sarang", "" + arrCheckBox[1][1].getY());
            Log.d("sarang", "" + arrCheckBox[2][2].getX());
            Log.d("sarang", "" + arrCheckBox[2][2].getY());

            if (mBluetoothLeService != null)
            {
                String s = buttonView.getTag().toString();
                int x = Integer.parseInt(s.split(",")[1]);
                int vertical = (x / 8) * 8;
                Log.d(TAG, "vertical" + vertical);
                int y = Integer.parseInt(s.split(",")[2]);
//                for(int i=vertical; i<vertical+8; i++)
//                {
//                    Log.d(TAG,i+","+arrCheckBox[y][i].isChecked());
//                }
                if (arrCheckBox[y][vertical + 7].isChecked()) value += 1;
                if (arrCheckBox[y][vertical + 6].isChecked()) value += 2;
                if (arrCheckBox[y][vertical + 5].isChecked()) value += 4;
                if (arrCheckBox[y][vertical + 4].isChecked()) value += 8;
                if (arrCheckBox[y][vertical + 3].isChecked()) value += 16;
                if (arrCheckBox[y][vertical + 2].isChecked()) value += 32;
                if (arrCheckBox[y][vertical + 1].isChecked()) value += 64;
                if (arrCheckBox[y][vertical + 0].isChecked()) value += 128;

//              box.setTag(String.format("*,%02d,%02d,001,$", j, i));

                String protocol = String.format("*,%02d,%02d,%03d,$", x / 8, y, value);
                Log.d(TAG, protocol);
                mBluetoothLeService.write(protocol);
            }
        }
    };

    CheckBox arrCheckBox[][] = new CheckBox[layoutIds.length][itemids.length];
    LinearLayout arrLayout[] = new LinearLayout[layoutIds.length];
    private TextView position;

    boolean bDrawCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarang);

        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setCustomView(R.layout.action_bar);
        CheckBox drawCheck = (CheckBox) getActionBar().getCustomView().findViewById(R.id.drawCheck);
        drawCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                bDrawCheck = isChecked;
            }
        });
        position = (TextView) findViewById(R.id.position);

        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);

        for (int i = 0; i < layoutIds.length; i++)
        {
            for (int j = 0; j < itemids.length; j++)
            {
                CheckBox box = (CheckBox) findViewById(layoutIds[i]).findViewById(itemids[j]);
                arrCheckBox[i][j] = box;

                box.setTag(String.format("*,%02d,%02d,001,$", j, i));
                box.setOnCheckedChangeListener(mOnCheckedChangeListener);
                box.setOnTouchListener(mOntouchListener);
            }
            arrLayout[i] = (LinearLayout) findViewById(layoutIds[i]);
        }

        findViewById(R.id.line1).setOnTouchListener(mOntouchListener);
    }

    View.OnTouchListener mOntouchListener = new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            position.setText("y:" + event.getRawX() + ",x: " + event.getRawY());
            for (int i = 0; i < layoutIds.length - 1; i++)
            {
                for (int j = 0; j < itemids.length - 1; j++)
                {
                    if (event.getRawX() - 30 > arrCheckBox[i][j].getX() && event.getRawX() - 30 <= arrCheckBox[i][j + 1].getX()
                            && event.getRawY() - 190 > arrLayout[i].getY() && event.getRawY() - 190 <= arrLayout[i + 1].getY())
                    {
                        if (!arrCheckBox[i][j].isChecked() && bDrawCheck)
                        {
                            arrCheckBox[i][j].performClick();
                        } else if (arrCheckBox[i][j].isChecked() && !bDrawCheck)
                        {
                            arrCheckBox[i][j].performClick();
                        }
                        return false;
                    }
                }
            }
            return false;
        }
    };

    @Override
    protected void onResume()
    {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null)
        {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action))
            {
                mConnected = true;
//                updateConnectionState(R.string.connected);
                invalidateOptionsMenu();
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action))
            {
                mConnected = false;
//                updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu();
//                clearUI();
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action))
            {
                // Show all the supported services and characteristics on the user interface.
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action))
            {

            }
        }
    };

    private void displayGattServices(List<BluetoothGattService> gattServices)
    {
        if (gattServices == null) return;
        String uuid = null;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData
                = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices)
        {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(
                    LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData =
                    new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics =
                    gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas =
                    new ArrayList<BluetoothGattCharacteristic>();

            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics)
            {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            if (charas.size() > 0)
            {
                mGattCharacteristics.add(charas);
                mBluetoothLeService.setGattCharacteristics(charas.get(charas.size() - 1));
                gattCharacteristicData.add(gattCharacteristicGroupData);
            }
        }
    }

    private static IntentFilter makeGattUpdateIntentFilter()
    {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    private final ServiceConnection mServiceConnection = new ServiceConnection()
    {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service)
        {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize())
            {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            mBluetoothLeService = null;
        }
    };
}


