package com.company.maps.view.commune;

import com.company.maps.entity.Commune;
import com.company.maps.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.event.MapClickEvent;
import io.jmix.mapsflowui.component.model.FitOptions;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "communes/:id", layout = MainView.class)
@ViewController(id = "Commune.detail")
@ViewDescriptor(path = "commune-detail-view.xml")
@EditedEntityContainer("communeDc")
public class CommuneDetailView extends StandardDetailView<Commune> {

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
    public void onInitEntity(final InitEntityEvent<Commune> event) {
        editBuildingButton.setVisible(false);
    }

    @Subscribe(id = "editBuildingButton", subject = "clickListener")
    public void onEditBuildingButtonClick(final ClickEvent<JmixButton> event) {
        notifications.show("Click on map to select the commune location");
    }

    @Subscribe("map")
    public void onMapMapClick(final MapClickEvent event) {
        Point point = geometryFactory.createPoint(event.getCoordinate());
        Commune commune = getEditedEntity();
        commune.setCoordinates(point);
    }

    private void setMapCenter(Geometry center) {
        map.fit(new FitOptions(center).withMaxZoom(18d));
    }
}