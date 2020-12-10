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

public class Music extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView tformusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
         bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Music_section);
        try {
            ArrayList<HashMap<String, String>> userList = new ArrayList<>();
            tformusic = findViewById(R.id.tformusic);
            InputStream is = getAssets().open("musicData.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setCoalescing(true);///impotant learn


            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("itune");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) nList.item(i);

                    tformusic.setText(tformusic.getText() + "\nuniqueID :" + getElement("uniqueID", ele) + "\n");
                    tformusic.setText(tformusic.getText() + "ProduktID :" + getElement("ProduktID", ele) + "\n");
                    tformusic.setText(tformusic.getText() + "Type :" + getElement("Type", ele) + "\n");
                    tformusic.setText(tformusic.getText() + "Kategorie  :" + getElement("Kategorie", ele) + "\n");
                    tformusic.setText(tformusic.getText() + "ShortName :" + getElement("ShortName", ele) + "\n");
                    tformusic.setText(tformusic.getText() + "FolgeNo :" + getElement("FolgeNo", ele) + "\n");
                    tformusic.setText(tformusic.getText() + "ReleaseDate" + getElement("ReleaseDate", ele) + "\n");
                    tformusic.setText(tformusic.getText() + "Artist" + getElement("Artist", ele) + "\n");
                    tformusic.setText(tformusic.getText() + "Title" + getElement("Title", ele) + "\n");
//                    tformusic.setText(tformusic.getText() + "AutorScript" + getElement("AutorScript", ele) + "\n");
//                    tformusic.setText(tformusic.getText() + "AutorBuch" + getElement("AutorBuch", ele) + "\n");
//                    tformusic.setText(tformusic.getText() + "RegProduction" + getElement("AutorBuch", ele) + "\n");
                    tformusic.setText(tformusic.getText() + "---------------------------------------------------------");


                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                List<MenuItem> menuItems = new ArrayList<>();
                switch (id)
                {

                    case R.id.Books_section:
                        Intent web=new Intent(Music.this,MainActivity.class);
                        startActivity(web);
                        break;

                    case R.id.Music_section:

                        break;
                    case R.id.Games_section:
                        Intent about=new Intent(Music.this,Games.class);
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
