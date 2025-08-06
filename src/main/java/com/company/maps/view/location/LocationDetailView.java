package com.company.maps.view.location;

import com.company.maps.entity.Location;
import com.company.maps.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.event.MapClickEvent;
import io.jmix.mapsflowui.component.model.FitOptions;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "locations/:id", layout = MainView.class)
@ViewController("Location.detail")
@ViewDescriptor("location-detail-view.xml")
@EditedEntityContainer("locationDc")
public class LocationDetailView extends StandardDetailView<Location> {

    protected GeometryFactory geometryFactory = GeometryUtils.getGeometryFactory();

    @Autowired
    private Notifications notifications;

    @ViewComponent
    private GeoMap map;

    @ViewComponent
    private JmixButton editBuildingButton;

    @Subscribe
    public void onInit(final InitEvent event) {
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        setMapCenter(getEditedEntity().getCoordinates());
    }

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Location> event) {
        editBuildingButton.setVisible(false);
    }

    @Subscribe(id = "editBuildingButton", subject = "clickListener")
    public void onEditBuildingButtonClick(final ClickEvent<JmixButton> event) {
        notifications.show("Click on map to select the building location");
    }

    @Subscribe("map")
    public void onMapMapClick(final MapClickEvent event) {
            Point point = geometryFactory.createPoint(event.getCoordinate());
            Location location = getEditedEntity();
            location.setCoordinates(point);
    }

    private void setMapCenter(Geometry center) {
        map.fit(new FitOptions(center).withMaxZoom(18d));
    }

}