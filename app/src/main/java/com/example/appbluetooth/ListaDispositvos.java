package com.example.appbluetooth;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.Set;

public class ListaDispositvos extends ListActivity {

    private BluetoothAdapter meuBluetoothAdapter = null;
    static String ENDERECO_MAC = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> DispositivosPareados = meuBluetoothAdapter.getBondedDevices();

        if(DispositivosPareados.size()>0){
            for (BluetoothDevice dispositivo : DispositivosPareados){
                String nomeDp = dispositivo.getName();
                String macDp = dispositivo.getAddress();
                ArrayBluetooth.add(nomeDp + "\n" + macDp);
            }
            setListAdapter(ArrayBluetooth);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String informacaoGeral = ((TextView) v).getText().toString();
        //Toast.makeText(getApplicationContext(),"Info" + informacaoGeral, Toast.LENGTH_SHORT).show();

        String enderecoMAC = informacaoGeral.substring(informacaoGeral.length() - 17);

       // Toast.makeText(getApplicationContext(),"MAC : " + enderecoMAC, Toast.LENGTH_SHORT).show();

        Intent retornaMAC = new Intent();
        retornaMAC.putExtra(ENDERECO_MAC, enderecoMAC);
        setResult(RESULT_OK, retornaMAC);
        finish();
    }
}
