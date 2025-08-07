package com.company.maps.view.province;

import com.company.maps.entity.Province;
import com.company.maps.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "provinces/:id", layout = MainView.class)
@ViewController(id = "Province.detail")
@ViewDescriptor(path = "province-detail-view.xml")
@EditedEntityContainer("provinceDc")
public class ProvinceDetailView extends StandardDetailView<Province> {
}