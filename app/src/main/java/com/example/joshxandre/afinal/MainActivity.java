package com.example.joshxandre.afinal;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btnScanner;
    private Button btnSms;
    private Button btnEmail;
    private TextView tp;
    private String message;
    private double tprice = 0.0f;
    private ArrayList<ShoppingC> List = new ArrayList<ShoppingC>();
    ShoppingC shoppingAdd = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (List.isEmpty()) {
            Toast.makeText(this, "Shopping Cart is Empty!", Toast.LENGTH_LONG).show();
        }
        listView = (ListView) findViewById(R.id.listView);
        btnEmail = (Button) findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!List.isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");

                    intent.putExtra(Intent.EXTRA_TEXT, message);

                    startActivity(Intent.createChooser(intent, "Send Email"));


                }

            }
        });
        tp= (TextView) findViewById(R.id.tp);
        btnSms= (Button) findViewById(R.id.btnSMS);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!List.isEmpty()){
                    Intent sIntent = new Intent(android.content.Intent.ACTION_VIEW);
                    sIntent.setType("vnd.android-dir/mms-sms");
                    sIntent.putExtra("address", "");
                    sIntent.putExtra("sms_body", message);
                    startActivity(sIntent);}
            }
        });


    }
    public void onClick(View v) {
        try {
            Intent startScanner = new Intent(getApplicationContext(), Scanner.class);
            startActivityForResult(startScanner, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    String mdata = data.getStringExtra("myData");
                    addToList(mdata);
                } catch (Exception e) {
                    AlertDialog.Builder diagBuilder = new AlertDialog.Builder(this);
                    diagBuilder.setTitle("Invalid");
                    diagBuilder.setMessage("Invalid QR Format");

                    AlertDialog mAlert = diagBuilder.create();
                    mAlert.show();
                    Thread mThread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                sleep(2000);
                                Intent startScanner = new Intent(getApplicationContext(), Scanner.class);
                                startActivityForResult(startScanner, 1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    mThread.start();
                }


            }
        }
    }

    public void addToList(String mydata) {

        int count = 0;
        ArrayList<String> stringArray = new ArrayList<String>();
        StringTokenizer myTokenizer = new StringTokenizer(mydata, "|||");
        while (myTokenizer.hasMoreTokens()) {
            stringArray.add(myTokenizer.nextToken());
        }

        shoppingAdd = new ShoppingC(stringArray.get(0), Integer.parseInt(stringArray.get(1)),
                Double.parseDouble(stringArray.get(2)), Integer.parseInt(stringArray.get(1))
                * Double.parseDouble(stringArray.get(2)));
        tprice += (Integer.parseInt(stringArray.get(1)) * Double.parseDouble(stringArray.get(2)));
        List.add(shoppingAdd);
        tp.setText(tprice + "");
        ScanAdapt adapt = new ScanAdapt (this, R.layout.activity_scanner, List);
      listView.setAdapter( adapt);

        for (int i = 0; i < adapt.getCount(); i++) {
            message = message + adapt.getItem(i).getItemName() + " " + adapt.getItem(i).getQty() + " " + adapt.getItem(i).getPrice() + "\n";

        }
        message = message + "Total" + tprice;

    }

}