package com.company.maps.view.geomapclusterv1;


import com.company.maps.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "GeoMapClusterV1", layout = MainView.class)
@ViewController(id = "Geomapclusterv1")
@ViewDescriptor(path = "GeoMapClusterV1.xml")
public class Geomapclusterv1 extends StandardView {
}