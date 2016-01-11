package com.example.jisuanqi.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.jisuanqi.R;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private EditText editText;
    private String str;
    private int i;
    private String num1;
    private static int mid = 0;
    private Double result = 0D;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] array = {"(", ")", "C", "清除",
                          "1", "2", "3", "/",
                          "4", "5", "6", "X",
                          "7", "8", "9", "+",
                          ".", "0", "=", "-"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        gridView = (GridView) findViewById(R.id.gl_view);
        gridView.setAdapter(adapter);
        editText = (EditText) findViewById(R.id.editText);


        initJisuanqi();


    }

    private void initJisuanqi() {
//        try {
//            if (str != null && str.length() > 0) {
//                i = Integer.parseInt(str);
//            }
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (result > 0D) {
                    editText.setText("");
                    result = 0d;
                }
                switch (position) {
                    case 0:
                        editText.setText(editText.getText() + "(");
                        break;
                    case 1:
                        editText.setText(editText.getText() + ")");
                        break;
                    case 2:
                        editText.setText("");
                        break;
                    case 3:
                        str = editText.getText().toString();
                        int i = Integer.parseInt(str);
//                        if (TextUtils.isEmpty(str)) return;
//                        else {
                        //这样做只能实现整数的退位。。。而且对带.的数使用会crash
//                        Log.d("MainActivity",""+i);
                        if(i < 10){

                            editText.setText("");
                        } else {
                            i = i / 10;
                            editText.setText(i + "");
                        }

//                        }
                        break;
                    case 4:
                        editText.setText(editText.getText() + "1");
                        break;
                    case 5:
                        editText.setText(editText.getText() + "2");
                        break;
                    case 6:
                        editText.setText(editText.getText() + "3");
                        break;
                    case 7:
                        //editText.setText(editText.getText() + "/");
                        sub(position);
                        break;
                    case 8:
                        editText.setText(editText.getText() + "4");
                        break;
                    case 9:
                        editText.setText(editText.getText() + "5");
                        break;
                    case 10:
                        editText.setText(editText.getText() + "6");
                        break;
                    case 11:
                        //editText.setText(editText.getText() + "*");
                        sub(position);
                        break;
                    case 12:
                        editText.setText(editText.getText() + "7");
                        break;
                    case 13:
                        editText.setText(editText.getText() + "8");
                        break;
                    case 14:
                        editText.setText(editText.getText() + "9");
                        break;
                    case 15:
                        //editText.setText(editText.getText() + "+");
                        sub(position);
                        break;
                    case 16:
                        editText.setText(editText.getText() + ".");
                        break;
                    case 17:
                        if (str.equals("0")) {
                            editText.setText("0");
                        }
                        editText.setText(editText.getText() + "0");
                        break;
                    case 18:
                        //editText.setText(editText.getText() + "=");
                        equl();
                        break;
                    case 19:
                        //editText.setText(editText.getText() + "-");
                        sub(position);
                        break;
                    default:
                        break;


                }

                editText.setSelection(editText.getText().length());
            }
        }

        );
    }

    private Double equl() {
        String num2;
        num2 = editText.getText().toString();
        String f ;
        if (num2.equals("")){
           num2 = "0";
        }
        result = 0d;
        if (mid == 15) {

            result = Double.parseDouble(num1) + Double.parseDouble(num2);
            f = "+";
            editText.setText(num1 + f + num2 + "=" + result);
        } else if (mid == 19) {
            result = Double.parseDouble(num1) - Double.parseDouble(num2);
            f = "-";
            editText.setText(num1 + f + num2 + "=" + result);
        } else if (mid == 11) {
            result = Double.parseDouble(num1) * Double.parseDouble(num2);
            f = "*";
            editText.setText(num1 + f + num2 + "=" + result);
        } else if (mid == 7) {
            if(num2.equals("0")){
                Toast.makeText(MainActivity.this,"除数不能为0",Toast.LENGTH_SHORT ).show();
                return -1d;
            }
            result = Double.parseDouble(num1) / Double.parseDouble(num2);
            f = "/";
           editText.setText(num1 + f + num2 + "=" + result);
        }
        return result;
    }

    public String sub(int id) {
        mid = id;
        num1 = editText.getText().toString();
        editText.setText("");

        return num1;
    }


}
