package com.example.xmlparserassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    private  TextView t1;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ArrayList<HashMap<String, String>> userList = new ArrayList<>();
            t1 = findViewById(R.id.t1);
            InputStream is = getAssets().open("bookData.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setCoalescing(true);///impotant learn


            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("book");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) nList.item(i);

                    t1.setText(t1.getText() + "\nuniqueID :" + getElement("uniqueID", ele) + "\n");
                    t1.setText(t1.getText() + "ProductID :" + getElement("ProductID", ele) + "\n");
                    t1.setText(t1.getText() + "Type :" + getElement("Type", ele) + "\n");
                    t1.setText(t1.getText() + "ShortName :" + getElement("ShortName", ele) + "\n");
                    t1.setText(t1.getText() + "Title :" + getElement("Title", ele) + "\n");
                    t1.setText(t1.getText() + "AutorFName1 :" + getElement("AutorFName1", ele) + "\n");
                    t1.setText(t1.getText() + "Erscheinungsjahr" + getElement("Erscheinungsjahr", ele) + "\n");
                    t1.setText(t1.getText() + "OriginalausgabeLieferbar" + getElement("OriginalausgabeLieferbar", ele) + "\n");
                    t1.setText(t1.getText() + "Kurztext" + getElement("Kurztext", ele) + "\n");
                    t1.setText(t1.getText() + "Illustrator" + getElement("Illustrator", ele) + "\n");
                    t1.setText(t1.getText() + "---------------------------------------------------------");


                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                List<MenuItem> menuItems = new ArrayList<>();
                switch (id)
                {

                    case R.id.Books_section:
                        break;

                    case R.id.Music_section:
                        Intent web=new Intent(MainActivity.this,Music.class);
                        startActivity(web);


                        break;
                        case R.id.Games_section:
                        Intent about=new Intent(MainActivity.this,Games.class);
                        startActivity(about);
                    default:
                        return true;
                }


                return true;

            }
        });

    }


    public static String getElement(String tag,Element e) {
        NodeList nodeList = e.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
//        Element e1=(Element) list.item(0);
//        String data;
//
//        Node child = e1.getFirstChild();
//
//        for (int index = 0; index < list.getLength(); index++) {
//            if (child instanceof CharacterData) {
//                CharacterData cd = (CharacterData) child;
//                data = cd.getData();
//                if (data != null && data.trim().length() > 0)
//                    return  ((CharacterData) child).getData();
//            }
//        }
//        return "";
    }
}