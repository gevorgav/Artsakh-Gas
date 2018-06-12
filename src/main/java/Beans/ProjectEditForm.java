package Beans;

import Core.Root;

/**
 * Created by arshak.askaryan on 2/5/2016.
 */
public class ProjectEditForm {

    private Root root;

    private Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public ProjectEditForm() {

    }

    private String mapWorld = "{\n" +
            "        map: 'world_mill_en',\n" +
            "        markers: [{latLng: [52.5167, 13.3833], name: 'Berlin'}, {latLng: [48.8567, 2.3508], name: 'Paris'}, {latLng: [35.6833, 139.6833], name: 'Tokyo'}, {latLng: [40.7127, -74.0059], name: 'New York City'},{latLng: [49.2827, -123.1207], name: 'City of Vancouver'},{latLng: [22.2783, 114.1747], name: 'Hong Kong'},{latLng: [55.7500, 37.6167], name: 'Moscow'},{latLng: [37.7833, -122.4167], name: 'San Francisco'},{latLng: [39.9167, 116.3833], name: 'Beijing'}],\n" +
            "        normalizeFunction: 'polynomial',\n" +
            "        backgroundColor: 'transparent',\n" +
            "        regionsSelectable: true,\n" +
            "        markersSelectable: true,\n" +
            "        regionStyle: {\n" +
            "          initial: {\n" +
            "            fill: 'rgba(120,130,140,0.2)'\n" +
            "          },\n" +
            "          hover: {\n" +
            "            fill: '#a88add',\n" +
            "            stroke: '#fff'\n" +
            "          },\n" +
            "        },\n" +
            "        markerStyle: {\n" +
            "          initial: {\n" +
            "            fill: 'rgba(0,0,0,0.1)',\n" +
            "            stroke: '#fff'\n" +
            "          },\n" +
            "          hover: {\n" +
            "            fill: '#0cc2aa',\n" +
            "            stroke: '#fff'\n" +
            "          }\n" +
            "        },\n" +
            "        series: {\n" +
            "          markers: [{\n" +
            "            attribute: 'fill',\n" +
            "            scale: ['#0cc2aa','#a88add', '#fcc100'],\n" +
            "            values: [ 605.16, 310.69, 405.17, 748.31, 207.35, 217.22, 137.70, 280.71, 210.32, 325.42 ]\n" +
            "          },{\n" +
            "            attribute: 'r',\n" +
            "            scale: [5, 20],\n" +
            "            values: [ 605.16, 310.69, 405.17, 748.31, 207.35, 217.22, 137.70, 280.71, 210.32, 325.42 ]\n" +
            "          }]\n" +
            "        }\n" +
            "      }";

    public String getMapWorld() throws ClassNotFoundException {
//        YearDao yearDao = new YearDao();
//        Year year = new Year();
//        year.setId(2024);
//        year.setYear(2024);
//        yearDao.insert(year);
//        System.out.println(year + "Year inserted");

//        FacesContext.getCurrentInstance();
        return this.mapWorld;
    }

    public void setMapWorld(String mapWorld) {
        this.mapWorld = mapWorld;
    }
}
