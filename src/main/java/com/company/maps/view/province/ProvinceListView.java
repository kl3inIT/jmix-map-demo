package com.company.maps.view.province;

import com.company.maps.entity.Province;
import com.company.maps.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "provinces", layout = MainView.class)
@ViewController(id = "Province.list")
@ViewDescriptor(path = "province-list-view.xml")
@LookupComponent("provincesDataGrid")
@DialogMode(width = "64em")
public class ProvinceListView extends StandardListView<Province> {
}