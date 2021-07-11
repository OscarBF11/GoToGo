package com.example.proyecto.ui.home;

import android.Manifest;
import android.app.Notification;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto.ActivityNotificationChannels;
import com.example.proyecto.R;
import com.example.proyecto.ui.categories.entities.ObjectItem;
import com.example.proyecto.ui.home.entities.ObjectDangerousArea;
import com.example.proyecto.ui.home.data.DataHome;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentHome extends Fragment implements OnMapReadyCallback { //es necesario implementar esto

    private static GoogleMap map; //widget map
    private static Boolean actualPosition = true;
    private static Boolean createRoute = true;
    private static JSONObject jso;
    private static Double longitudeOrigin, latitudeOrigin;
    private static ArrayList<ObjectItem> selectedObjectItems = new ArrayList();
    private static boolean inSafeArea = true;
    private NotificationManagerCompat notificationManagerCompat;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationManagerCompat = NotificationManagerCompat.from(getActivity());

        if (selectedObjectItems == null || selectedObjectItems.isEmpty()) {
            try {
                getFragmentManager().beginTransaction().remove(this).commit();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map); //get and generate the map
        mapFragment.getMapAsync(this);
        return root;
    }

    @Override
    public void onDetach() { //in case this fragment lose focus, set createRoute true in order to generate another route
        super.onDetach();
        createRoute=true;
    }

    public static void fillSelectedItems(ArrayList<ObjectItem> objectItemArrayList) { //fill the arraylist with the selected items
        selectedObjectItems = objectItemArrayList;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, //check permissions
                                           @NonNull String permissions[], int[] grantResults) {
        if (requestCode == 1) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //check for permission and generate the map
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            return;
        }

        map.setMyLocationEnabled(true);
        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (actualPosition) { //every time the position of the user changes, check if is in a dangerous zone or not
                    latitudeOrigin = location.getLatitude(); //set actual latitude of the user
                    longitudeOrigin = location.getLongitude(); // set actual longitude of the user

                    showNotification(checkInDangerousArea());

                    if (createRoute) {//execute all methods related to map only once
                        createRoute = false;
                        String wpoints = deleteLastCharacter(generateWaypointsForQuery(latitudeOrigin, longitudeOrigin));
                        addDangerousAreas();
                        String lastPoint = lastRoutePoint(latitudeOrigin, longitudeOrigin);

                        map.getUiSettings().setZoomControlsEnabled(true); //Create two buttons in the map to zoom in and zoom out
                        LatLng p = new LatLng(latitudeOrigin, longitudeOrigin); //create a cardinal point
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(p).zoom(16).bearing(15).build();
                        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); //generate a small animation zooming in to your actual position in the map

                        String url = "https://maps.googleapis.com/maps/api/directions/json?origin="
                                + latitudeOrigin
                                + ","
                                + longitudeOrigin
                                + "&destination="
                                + lastPoint
                                + "&"
                                + wpoints
                                + "&key=AIzaSyAh6jTLj1vmGY-fNrINlyXKB_HtzcrWL8c"; //generate the query that will be provided to Google's route API in order to generate the route

                        RequestQueue queue = Volley.newRequestQueue(getActivity());
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {

                                try {
                                    jso = new JSONObject(response); //generate a json object
                                    drawRouteOnMap(jso);
                                    Log.i("jsonRuta: ", "" + response);
                                    System.out.println(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        queue.add(stringRequest);
                    }
                }
            }
        });
    }


    private String generateWaypointsForQuery(double latitude, double longitude) {
        //generate the waypoints of the route
        ArrayList<ObjectItem> itemsInArrayList = selectedObjectItems;
        double distanceBetweenPoints;
        String waypoints = "waypoints=";

        for (ObjectItem objectItem : itemsInArrayList) {
            distanceBetweenPoints = Math.hypot(objectItem.getCoordinateX() - latitude, objectItem.getCoordinateY() - longitude);
            objectItem.setDistance(distanceBetweenPoints);
        }
        Collections.sort(itemsInArrayList, new Comparator<ObjectItem>() {
            //sort the arraylist according to the distance between the user and the point to visit

            @Override
            public int compare(ObjectItem objectItem1, ObjectItem objectItem2) {
                return Double.compare(objectItem1.getDistance(), objectItem2.getDistance());
            }
        });

        if (itemsInArrayList.size() < 22) {
            //as the google's API can get a maximum of 23 waypoints, in case the user selected more than 23, we only get the nearest 23.

            for (ObjectItem objectItem : itemsInArrayList) {  //in case the user selected less than 23 points
                String coordinateXString = Double.toString(objectItem.getCoordinateX());
                String coordinateYString = Double.toString(objectItem.getCoordinateY());
                waypoints = waypoints.concat(coordinateXString + "," + coordinateYString + "|");//concat to the coordinates of the points to a string

                map.addMarker(new MarkerOptions() //also we add a marker in this position
                        .position(new LatLng(objectItem.getCoordinateX(), objectItem.getCoordinateY()))
                        .title(objectItem.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        } else {

            for (int i = 0; i < 22; i++) {
                //in case the user selected more than 23 points
                String coordinateXString = Double.toString(itemsInArrayList.get(i).getCoordinateX());
                String coordinateYString = Double.toString(itemsInArrayList.get(i).getCoordinateY());
                waypoints = waypoints.concat(coordinateXString + "," + coordinateYString + "|");
                map.addMarker(new MarkerOptions()
                        .position((new LatLng(itemsInArrayList.get(i).getCoordinateX(), itemsInArrayList.get(i).getCoordinateY())))
                        .title(itemsInArrayList.get(i).getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        }
        return waypoints;
    }

    private static String deleteLastCharacter(String waypoints) {
        //delete the las | of the string, because the query can't end with this character
        if (waypoints != null && waypoints.length() > 0 && waypoints.charAt(waypoints.length() - 1) == '|') {
            waypoints = waypoints.substring(0, waypoints.length() - 1);
        }
        return waypoints;
    }

    private String lastRoutePoint(double latitude, double longitude) {
        //this will set the last point of the route, sorting the arraylist by distance and getting the last point
        ArrayList<ObjectItem> itemsInArrayList = selectedObjectItems;
        double distanceBetweenPoints;
        String lastPoint;

        for (ObjectItem objectItem : itemsInArrayList) {
            distanceBetweenPoints = Math.hypot(objectItem.getCoordinateX() - latitude, objectItem.getCoordinateY() - longitude);
            objectItem.setDistance(distanceBetweenPoints);
        }
        Collections.sort(itemsInArrayList, new Comparator<ObjectItem>() {
            @Override
            public int compare(ObjectItem objectItem1, ObjectItem objectItem2) {
                return Double.compare(objectItem1.getDistance(), objectItem2.getDistance());
            }
        });

        if (itemsInArrayList.size() < 22) {
            ObjectItem getLastPoint = itemsInArrayList.get(itemsInArrayList.size() - 1);
            lastPoint = Double.toString(getLastPoint.getCoordinateX()) + "," + Double.toString(getLastPoint.getCoordinateY());
        } else {
            ObjectItem getLastPoint = itemsInArrayList.get(21);
            lastPoint = Double.toString(getLastPoint.getCoordinateX()) + "," + Double.toString(getLastPoint.getCoordinateY());
        }

        return lastPoint;
    }


    public static void addDangerousAreas() {
        //for each dangerous area, we draw a red circle in the map
        ArrayList<ObjectDangerousArea> objectDangerousAreasList = DataHome.generateArrayDangerousAreas();
        for (ObjectDangerousArea objectDangerousArea : objectDangerousAreasList) {
            map.addCircle(new CircleOptions() //we set the properties of the circle
                    .center(new LatLng(objectDangerousArea.getLatitude(), objectDangerousArea.getLogitude()))
                    .radius(200)
                    .strokeWidth(3f)
                    .strokeColor(Color.RED)
                    .fillColor(Color.argb(50, 255, 0, 0)));
        }
    }

    public static Boolean checkInDangerousArea() {
        //check if the user is in a dangerous area or not
        double distance;
        ArrayList<ObjectDangerousArea> objectDangerousAreas = DataHome.generateArrayDangerousAreas();
        double userToCenter = 0.0018252390155926344;

        for (ObjectDangerousArea z : objectDangerousAreas) {
            //to determine this, the distance between the center of the dangerous area and the user should be more than 0.0018252390155926344;
            distance = Math.hypot(z.getLatitude() - latitudeOrigin, z.getLogitude() - longitudeOrigin);
            if (distance < userToCenter) {
                inSafeArea = false;
            }
        }
        return inSafeArea;
    }

    public void showNotification(Boolean isSafeArea) {
        // in case the user was in a dangerous area, this method will send him a notification
        if (!isSafeArea) {
            String title = "AVISO";
            String msg = "Estás en una zona conflictiva";
            Notification notification = new NotificationCompat.Builder(getActivity(), ActivityNotificationChannels.CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_noti)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();

            notificationManagerCompat.notify(1, notification); //send the notification
        }
    }


    private void drawRouteOnMap(JSONObject jso) {
        // decomposes the json into its parts and generates a route based on the information in this
        JSONArray jRoutes;
        JSONArray jLegs;
        JSONArray jSteps;

        try {
            jRoutes = jso.getJSONArray("routes");
            for (int i = 0; i < jRoutes.length(); i++) {
                jLegs = ((JSONObject) (jRoutes.get(i))).getJSONArray("legs");
                for (int j = 0; j < jLegs.length(); j++) {
                    jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");
                    for (int k = 0; k < jSteps.length(); k++) {
                        String polyline = "" + ((JSONObject) ((JSONObject) jSteps.get(k)).get("polyline")).get("points");
                        Log.i("end", "" + polyline);
                        List<LatLng> list = PolyUtil.decode(polyline); //save the information in the List
                        map.addPolyline(new PolylineOptions().addAll(list)
                                .color(Color.rgb(0, 0, 0)).width(20)); //generate the polyline according to the information of the list
                        map.addPolyline(new PolylineOptions().addAll(list)
                                .color(Color.argb(255, 122, 158, 229)).width(10));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}