package com.company.maps.view.district;

import com.company.maps.entity.District;
import com.company.maps.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "districts/:id", layout = MainView.class)
@ViewController(id = "District.detail")
@ViewDescriptor(path = "district-detail-view.xml")
@EditedEntityContainer("districtDc")
public class DistrictDetailView extends StandardDetailView<District> {
}