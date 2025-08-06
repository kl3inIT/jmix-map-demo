package com.company.maps.view.geomapcluster;


import com.company.maps.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "geo-map-cluster", layout = MainView.class)
@ViewController(id = "GeoMapCluster")
@ViewDescriptor(path = "geo-map-cluster.xml")
public class GeoMapCluster extends StandardView {
}