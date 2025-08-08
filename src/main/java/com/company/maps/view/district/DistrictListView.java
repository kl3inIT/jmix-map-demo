package com.company.maps.view.district;

import com.company.maps.entity.District;

import com.company.maps.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "districts", layout = MainView.class)
@ViewController(id = "District.list")
@ViewDescriptor(path = "district-list-view.xml")
@LookupComponent("districtsDataGrid")
@DialogMode(width = "64em")
public class DistrictListView extends StandardListView<District> {
}