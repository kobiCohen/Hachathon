package org.college.android.itomer.recyclerviewswipetoremoveandmove;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rvDemo)
    RecyclerView rvDemo;

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_hd_movies);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        loadMoviesFromParse();
    }

    private void loadMoviesFromParse() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Messages");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override

            public void done(List<ParseObject> movies, ParseException e) {
                ArrayList<Contact> contactList = new ArrayList<Contact>();
                for (ParseObject m : movies) {
                    String name = m.getString("name");
                    String description = m.getString("description");
                    boolean read = m.getBoolean("read");

                    Contact movie = new Contact(name, description, read);
                    contactList.add(movie);
                }
                initRecyclerView(contactList);

            }
        });

    }

    private void initRecyclerView(List<Contact> contacts) {
        adapter = new RecyclerAdapter(contacts);
        rvDemo.setAdapter(adapter);
        rvDemo.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        initItemTouchHelper();
    }


    private void initItemTouchHelper() {
        //Adding Item Touch Helper
        ItemTouchCallbacks cal = new ItemTouchCallbacks(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(cal);
        helper.attachToRecyclerView(rvDemo);
    }

    private List<Contact> initDummyData() {
        List<Contact> movies = new ArrayList<>();
        /*for (int i = 0; i < 2; i++) {
            movies.add(new Contact("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", "http://images.fandango.com/MDCsite/images/featured/201512/the-hidden-plot-of-batman-vs-superman-dawn-of-justice-593860.jpg"));
            movies.add(new Contact("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", "https://c2.staticflickr.com/8/7446/15818000683_c49472e88b_b.jpg"));
            movies.add(new Contact("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.", "http://d.ibtimes.co.uk/en/full/1416337/captain-america-3.jpg"));
            movies.add(new Contact("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", "https://upload.wikimedia.org/wikipedia/en/e/e6/Kung_Fu_Panda_3_poster.jpg"));
            movies.add(new Contact("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth.", "http://assets.vg247.com/current//2015/07/warcraft_movie_lothar.jpg-large.jpg"));
            movies.add(new Contact("Alice in Wonderland", "Alice in Wonderland: Through the Looking Glass ", "http://vignette1.wikia.nocookie.net/disney/images/7/7e/Alice_In_Wonderland_(2010)_cover.jpg/revision/latest?cb=20120519233959"));
        }*/

        return movies;
    }

/*    @OnClick(R.id.fab)
    void clickedFab(View fab) {
        View.OnClickListener addItems = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItems(initDummyData());
                rvDemo.scrollToPosition(0);
            }
        };

        Snackbar.make(fab, "Add Dummy Items", Snackbar.LENGTH_INDEFINITE)
                .setAction("Add!", addItems).show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        System.out.println(item);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
