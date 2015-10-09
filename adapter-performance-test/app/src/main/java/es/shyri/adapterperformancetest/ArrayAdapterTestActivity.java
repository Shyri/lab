package es.shyri.adapterperformancetest;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import es.shyri.adapterperformancetest.adapter.NewsArrayAdapter;
import es.shyri.adapterperformancetest.database.NewsItemsDataSource;
import es.shyri.adapterperformancetest.database.NewsItemsProvider;
import es.shyri.adapterperformancetest.model.NewsItem;

/**
 * Created by Shyri on 07/10/2015.
 */
public class ArrayAdapterTestActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter_test);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

//    private void initDatabase() {
//        for (int i =0; i < 400; i++) {
//            datasource.createNewsEntry("ISS Daily Summary Report – 10/6/15" + i,
//                    "NanoRack Cubesat Deployer (NRCSD) #6 Operations:  Yesterday, two Danish cubesats and the first 4 Dove cubesats of the “Flock 2” fleet of satellites were launched. " + i,
//                    "NanoRack Cubesat Deployer (NRCSD) #6 Operations:  Yesterday, two Danish cubesats and the first 4 Dove cubesats of the “Flock 2” fleet of satellites were launched.  This morning an additional 4 Dove cubesats were launched and the final 6 Dove cubesats will be launched tonight.  The Dove nanosatellites enable imagery of the entire planet to be taken on a frequent basis, with humanitarian and environmental applications ranging from monitoring deforestation and the ice caps to disaster relief and improving agriculture yields in developing nations.  A total of 16 cubesats will be deployed this week:  14 Planet Lab Doves and the 2 Danish satellites.\n" +
//                            "\n" +
//                            " \n" +
//                            "\n" +
//                            "Sleep Log:  Kelly and Kornienko continued a week-long set of Sleep Log entries by making daily entries.  The Sleep ISS-12 experiment monitors ambient light exposure and crew member activity and collects subjective evaluations of sleep and alertness.  The investigation examines the effects of space flight and ambient light exposure on sleep during a year-long mission on the ISS.\n" +
//                            "\n" +
//                            " \n" +
//                            "\n" +
//                            "Node 1, 2 and 3 Cable Routes:  As part of USOS Reconfiguration, Lindgren finished an activity he started yesterday to install power and data cables within Node 1.  Once complete, he re-installed all of the closeout panels he had removed in support of this activity.  Yui, routed power cables from the Node 2 DC-to-DC Converter Unit (DDCU) Rack to the Node 2 Aft Bulkhead.  The cables will eventually provide redundant power to Node 1 Nadir Berthing Port and Node 1 Galley Rack.  Kelly re-routed a Node 3 Sample Delivery System (SDS) power cable which will provide power to a new Node 1 Deck SDS Valve. This cable was previously routed, however laid short of the connection point.  The Node 3 cable routing called for a widespread powerdown of Node 3 systems, including the Node 3-1 MDM, OGA, MCA, and CDRA.  The Lab CDRA was activated for CO2 removal.\n" +
//                            "\n" +
//                            " \n" +
//                            "\n" +
//                            "Remote Power Control Module (RPCM) N31B4A F1 Change Out:  Kelly replaced RPCM N31B4A F1 which is located behind Node 3 Avionics Rack #1.  The RPCM had experienced a switch anomaly in March 2015 which prevented power distribution to Audio Bus Coupler (ABC)-5, an Orbital Replacement Unit (ORU) that provides a redundant Audio path for voice and Caution/Warning Tones to and from Node 3 and Cupola Modules.\n" +
//                            "\n" +
//                            " \n" +
//                            "\n" +
//                            "Russian Treadmill (БД-2) Repair:  Today, Kornienko and Kononenko have replaced a broken Thrust Compensator Bracket on the Russian Treadmill (БД-2).  The bracket was reported as broken last month.  With no onboard spares available, Russian Ground Teams manifested a replacement bracket onboard Progress 61P.  Mission Control Center (MCC)-M has given the Russian Crew a go for БД-2 exercise.  The Russian Crew had been using the US Treadmill 2 (T2) while БД-2 was out of service.\n" +
//                            "\n" +
//                            " \n" +
//                            "\n" +
//                            "Robotics Refueling Mission (RRM):  Yesterday, the Robotics Ground Controllers maneuvered Special Purpose Dexterous Manipulator (SPDM) Arm2 into position to continue the Robotics Refueling Mission (RRM) payload Task Board 3 (TB3) Science Operations.  All objectives for TB3 were completed. This evening and overnight, ground controllers will begin TB4 science operations." + i);
//        }
//    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,                                                                               // Context
                Uri.parse("content://" + NewsItemsProvider.CONTENT_AUTHORITY).buildUpon().appendPath(NewsItemsProvider.CONTENT_TYPE).build(),                                          // Uri
                null,                                                                               // Projection
                null,                                                                               // Selection Params
                null,                                                                               // Selection Args
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        ArrayList<NewsItem> newsEntries = new ArrayList<NewsItem>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NewsItem newsItem = NewsItemsDataSource.cursorToNewsEntry(cursor);
            newsEntries.add(newsItem);
            cursor.moveToNext();
        }
        cursor.close();

        NewsArrayAdapter adapter = new NewsArrayAdapter(this, newsEntries);
        ((ListView) findViewById(R.id.newsList)).setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
