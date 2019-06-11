package com.example.ranking;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, android.support.v7.widget.SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<Item> itemlist;
    private Menu menu;
    private Toolbar toolbar;
    private boolean filter;
    private CardView cardView;
    private TextView textView5;
    private boolean nameASC, marksAsc;
    RadioButton radioButton1,radioButton2,radioButton3;
    private ArrayList<Item> studentPass;
    private ArrayList<Item> studentFail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        itemlist = new ArrayList<>();
        nameASC=true;
        marksAsc=false;
        filter =true;
        textView5=(TextView)findViewById(R.id.textView5);
        cardView=(CardView) findViewById(R.id.cardView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] namearray = getResources().getStringArray(R.array.name_array);
        int[] marksArray = getResources().getIntArray(R.array.marks_array);
        for (int i = 0; i < namearray.length; i++) {
            String name = namearray[i];
            int marks = marksArray[i];
            Item item = new Item();
            item.setName(name);
            item.setMarks(marks);
            itemlist.add(item);

        }
        sortStudents(false, false);
        for (Item item : itemlist) {
            item.setRank(itemlist.indexOf(item) + 1);
        }

        for (Item item : itemlist) {
            System.out.println(item.getName());

        }
           myAdapter=new MyAdapter(this);
           recyclerView.setAdapter(myAdapter);
           myAdapter.getItemList1().addAll(itemlist);
           myAdapter.updateList();
    }
@Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu, menu);
        android.support.v7.widget.SearchView searchView= (android.support.v7.widget.SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(this);

        final android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.background_light));
        searchAutoComplete.setTextColor(getResources().getColor(android.R.color.white));
        searchAutoComplete.setHint("Search");
        searchView.setOnQueryTextListener(this);
        ImageView searchClose= searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setImageResource(R.drawable.close);


        MenuItem searchMenuItem = menu.findItem(R.id.search);
        searchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                menu.getItem(0).setVisible(false);
                menu.getItem(1).setVisible(false);
                menu.getItem(2).setVisible(false);
                menu.getItem(3).setVisible(false);
                menu.getItem(4).setVisible(false);
                menu.getItem(5).setVisible(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                menu.getItem(0).setVisible(true);
                menu.getItem(1).setVisible(true);
                menu.getItem(2).setVisible(true);
                menu.getItem(3).setVisible(true);
                menu.getItem(4).setVisible(true);
                menu.getItem(5).setVisible(true);

                System.out.println("On close search bar");
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case R.id.sortOnName:
                sortStudents(true, nameASC);
                myAdapter.getItemList1().clear();
                myAdapter.getItemList1().addAll(itemlist);

                myAdapter.updateList();

                nameASC = !nameASC;
                item.setIcon(nameASC ? R.drawable.atoz : R.drawable.ztoa);
                break;
            case R.id.sortOnMarks:
                sortStudents(false, marksAsc);
                myAdapter.getItemList1().clear();
                myAdapter.getItemList1().addAll(itemlist);

                myAdapter.updateList();

                System.out.println("After Search on marks");
                marksAsc = !marksAsc;
                item.setIcon(marksAsc ? R.drawable.sortascend : R.drawable.sortdecend);
                break;
            case R.id.filter:
                if(filter) {
                    AlertDialog();
                    filter = !filter;
                    item.setIcon(filter ? R.drawable.filters : R.drawable.cross);
                    menu.getItem(0).setVisible(false);
                    menu.getItem(1).setVisible(false);
                    menu.getItem(2).setVisible(false);
                    menu.getItem(4).setVisible(false);
                    menu.getItem(5).setVisible(false);

                }
                else if(!filter){

                    sortStudents(false,false);
                    myAdapter.getItemList1().clear();
                    myAdapter.getItemList1().addAll(itemlist);
                    myAdapter.updateList();
                    filter=!filter;
                    item.setIcon(filter ? R.drawable.filters : R.drawable.cross);
                    menu.getItem(0).setVisible(true);
                    menu.getItem(1).setVisible(true);
                    menu.getItem(2).setVisible(true);
                    menu.getItem(4).setVisible(true);
                    menu.getItem(5).setVisible(true);
                }

                break;

            case R.id.minimum:


                sortStudents(false,true);
                myAdapter.getItemList1().clear();
                myAdapter.getItemList1().add(itemlist.get(0));
                myAdapter.updateList();

                menu.getItem(0).setVisible(false);
                menu.getItem(1).setVisible(false);
                menu.getItem(2).setVisible(false);
                menu.getItem(4).setVisible(false);
                menu.getItem(5).setVisible(false);
                filter=!filter;

                menu.getItem(3).setIcon(filter ?R.drawable.filters:R.drawable.cross);
                break;

            case R.id.maximum:

                sortStudents(false,false);
                myAdapter.getItemList1().clear();
                myAdapter.getItemList1().add(itemlist.get(0));
                myAdapter.updateList();

                menu.getItem(0).setVisible(false);
                menu.getItem(1).setVisible(false);
                menu.getItem(2).setVisible(false);
                menu.getItem(4).setVisible(false);
                menu.getItem(5).setVisible(false);
                filter=!filter;
                menu.getItem(3).setIcon(filter? R.drawable.filters:R.drawable.cross);
                break;

        }

        return super.onOptionsItemSelected(item);
    }



    private void sortStudents(final boolean sortOnName, final boolean asc) {
        Collections.sort(itemlist, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                int result = 0;
                if (sortOnName) {
                    if (asc)
                        result = o1.getName().compareTo(o2.getName());
                    else result = o2.getName().compareTo(o1.getName());
                } else {
                    int marks1 = asc ? o1.getMarks() : o2.getMarks();
                    int marks2 = asc ? o2.getMarks() : o1.getMarks();

                    if (marks1 > marks2)
                        result = 1;
                    else if (marks1 < marks2)
                        result = -1;
                }
                return result;
            }
        });

    }


    private void AlertDialog(){
        final View buttonPressView=getLayoutInflater().inflate(R.layout.userinput,null);

        radioButton1= (RadioButton)buttonPressView.findViewById(R.id.radioButton1);
        radioButton2= (RadioButton) buttonPressView.findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)buttonPressView.findViewById(R.id.radioButton3);


        final AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                .setView(buttonPressView)
                .setTitle("Select The criterion")
                .create();
        alertDialog.setOnShowListener(
                new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {


                        radioButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                sortStudents(false,false);
                                myAdapter.getItemList1().clear();
                                myAdapter.getItemList1().addAll(itemlist);
                                  myAdapter.updateList();

                                alertDialog.dismiss();
                            }
                        });
                        radioButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.out.println("Passed Selected");

                                sortStudents(false,false);
                                 studentPass=new ArrayList<>();
                                for(int i=0;i<itemlist.size();i++)
                                {
                                    if(itemlist.get(i).getMarks()>=35){
                                        studentPass.add(itemlist.get(i));

                                    }
                                }
                                    myAdapter.getItemList1().clear();
                                myAdapter.getItemList1().addAll(studentPass);
                                    myAdapter.updateList();
                                alertDialog.dismiss();
                            }
                        });
                        radioButton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                sortStudents(false,false);
                                 studentFail=new ArrayList<>();
                                for(int i=0;i<itemlist.size();i++){
                                    if(itemlist.get(i).getMarks()<35){
                                        studentFail.add(itemlist.get(i));
                                    }
                                }
                                myAdapter.getItemList1().clear();
                                myAdapter.getItemList1().addAll(studentFail);
                               myAdapter.updateList();

                                alertDialog.dismiss();
                            }
                        });

                    }});
        alertDialog.show();

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput=newText.toLowerCase();
        myAdapter.getItemList1().clear();

        for(Item string: itemlist) {
            if (string.getName().toLowerCase().contains(userInput))
                myAdapter.getItemList1().add(string);
        }

        if(myAdapter.getItemList1().isEmpty()){
            recyclerView.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            textView5.setVisibility(View.VISIBLE);

        }
        else{
            recyclerView.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
            textView5.setVisibility(View.GONE);

        }
        myAdapter.updateList();
        return true;
    }

}







