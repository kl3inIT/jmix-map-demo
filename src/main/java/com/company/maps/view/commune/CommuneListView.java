package com.company.maps.view.commune;

import com.company.maps.entity.Commune;
import com.company.maps.view.main.MainView;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.model.FitOptions;
import io.jmix.mapsflowui.component.model.layer.VectorLayer;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.kit.component.model.Extent;
import io.jmix.mapsflowui.kit.component.model.Padding;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateArrays;
import org.locationtech.jts.geom.Envelope;

import java.util.Optional;


@Route(value = "communes", layout = MainView.class)
@ViewController(id = "Commune.list")
@ViewDescriptor(path = "commune-list-view.xml")
@LookupComponent("communesDataGrid")
@DialogMode(width = "64em")
public class CommuneListView extends StandardListView<Commune> {

    @ViewComponent
    private InstanceContainer<Commune> communeDc;

    @ViewComponent
    private GeoMap map;
    @ViewComponent("map.buildingLayer")
    private VectorLayer mapBuildingLayer;
    @ViewComponent
    private CollectionContainer<Commune> communesDc;

    @Subscribe
    public void onInit(final InitEvent event) {
    }

    @Subscribe(id = "communesDc", target = Target.DATA_CONTAINER)
    public void onCommunesDcItemChange(final InstanceContainer.ItemChangeEvent<Commune> event) {
        communeDc.setItem(event.getItem());
    }

    @Subscribe("communesDataGrid")
    public void onCommunesDataGridSelection(final SelectionEvent<DataGrid<Commune>, Commune> event) {
        Optional<Commune> optionalCommune = event.getFirstSelectedItem();
        if (optionalCommune.isPresent()) {
            mapBuildingLayer.setVisible(false);

            Commune commune = optionalCommune.get();
            map.fit(new FitOptions(commune.getCoordinates()).withMaxZoom(19d));

        } else {
            mapBuildingLayer.setVisible(true);
            map.fit(new FitOptions(getAllVisibleCommunesExtent())
                    .withPadding(new Padding(20, 20, 20, 20))
                    .withMaxZoom(5d));
        }
    }

    private Extent getAllVisibleCommunesExtent() {
        Coordinate[] coordinates = communesDc.getItems().stream()
                .map(com -> com.getCoordinates())
                .toArray(Coordinate[]::new);

        Envelope envelope = CoordinateArrays.envelope(coordinates);
        return new Extent(envelope.getMinX(), envelope.getMinY(), envelope.getMaxX(), envelope.getMaxY());
    }
}