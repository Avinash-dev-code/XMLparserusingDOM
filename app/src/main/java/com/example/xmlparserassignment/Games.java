package com.example.xmlparserassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

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

public class Games extends AppCompatActivity {

    TextView tforgames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        try {
            ArrayList<HashMap<String, String>> userList = new ArrayList<>();
            tforgames = findViewById(R.id.tforgames);
            InputStream is = getAssets().open("gamesData.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setCoalescing(true);///impotant learn


            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("game");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) nList.item(i);

                    tforgames.setText(tforgames.getText() + "\nuniqueID :" + getElement("uniqueID", ele) + "\n");
                    tforgames.setText(tforgames.getText() + "ProductID :" + getElement("ProductID", ele) + "\n");
                    tforgames.setText(tforgames.getText() + "Type :" + getElement("Type", ele) + "\n");
                    tforgames.setText(tforgames.getText() + "ShortName :" + getElement("ShortName", ele) + "\n");
                    tforgames.setText(tforgames.getText() + "Title :" + getElement("Title", ele) + "\n");
                    tforgames.setText(tforgames.getText() + "Publisher :" + getElement("Publisher", ele) + "\n");
                    tforgames.setText(tforgames.getText() + "Entwickler :" + getElement("Entwickler", ele) + "\n");
                    tforgames.setText(tforgames.getText() + "USK :" + getElement("USK", ele) + "\n");
                    tforgames.setText(tforgames.getText() + "Platform :" + getElement("Platform", ele) + "\n");
                    tforgames.setText(tforgames.getText() + "---------------------------------------------------------");


                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Games_section);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                List<MenuItem> menuItems = new ArrayList<>();
                switch (id)
                {

                    case R.id.Books_section:
                        Intent about=new Intent(Games.this,MainActivity.class);
                        startActivity(about);
                        break;

                    case R.id.Music_section:
                        Intent web=new Intent(Games.this,Music.class);
                        startActivity(web);


                        break;
                    case R.id.Games_section:

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
