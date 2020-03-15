package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sarang on 2016-07-20.
 */
public class BLEManager
{
    private Activity mActivity;
    private BluetoothManager mBluetoothManager;
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter mBluetoothAdapter;
    private Handler mHandler;
    private boolean mScanning;
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    BLEManager(Activity activity)
    {
        mActivity = activity;
        mBluetoothManager = (BluetoothManager) mActivity.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        mHandler = new Handler();
    }


    //현재 단말기가 BLE를 지원하는지 체크
    boolean isSupportBLE()
    {
        if (!mActivity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE))
        {
            Toast.makeText(mActivity, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mBluetoothManager == null)
        {
            return false;
        } else
        {
            return true;
        }
    }

    public BluetoothAdapter getAdapter()
    {
        return mBluetoothAdapter;
    }

    //블루투스가 꺼져있다면 요청 OnResume시 사용 권장
    public void requestBluetoothON()
    {
        if (!mBluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mActivity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    public void scanLeDevice(final boolean enable)
    {
        if (mLeScanCallback == null)
            return;

        if (enable)
        {
            // Stops scanning after a pre-defined scan period.
            mLeDevices = new ArrayList<>();
            mHandler.postDelayed(new Runnable()
            {

                @Override
                public void run()
                {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    mActivity.invalidateOptionsMenu();
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else
        {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
        mActivity.invalidateOptionsMenu();
    }

    public void setLeScanCallback(BluetoothAdapter.LeScanCallback callback)
    {
        mLeScanCallback = callback;
    }


    LinearLayout mLayout;

    public void showSamplePopup()
    {
        Dialog d = new Dialog(mActivity);
        d.setCancelable(true);
        mLayout = new LinearLayout(mActivity);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        d.addContentView(mLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        d.show();
        scanLeDevice(true);
    }

    private ArrayList<BluetoothDevice> mLeDevices = new ArrayList<>();
    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback()
            {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord)
                {
                    mActivity.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            TextView tv = new TextView(mActivity);
                            tv.setText(device.getAddress());
                            if (mLayout != null)
                            {
                                if (!mLeDevices.contains(device))
                                {
                                    mLeDevices.add(device);
                                    final String deviceName = device.getName();
                                    if (deviceName != null && deviceName.length() > 0)
                                        tv.setText(deviceName);
                                    else
                                        tv.setText(R.string.unknown_device);

                                    tv.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v)
                                        {
                                            if (device == null) return;
                                            final Intent intent = new Intent(mActivity, DeviceControlActivity.class);
//                                            final Intent intent = new Intent(this, SarangActivity.class);
                                            intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, device.getName());
                                            intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                                            if (mScanning)
                                            {
                                                mBluetoothAdapter.stopLeScan(mLeScanCallback);
                                                mScanning = false;
                                            }
                                            mActivity.startActivity(intent);
                                        }
                                    });

                                    mLayout.addView(tv);
                                }
                            }
                        }
                    });
                }
            };

    public boolean isScanning()
    {
        return mScanning;
    }
}
