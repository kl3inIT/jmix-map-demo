package com.company.maps.view.geomapcluster;

import com.company.maps.entity.Commune;
import com.company.maps.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.event.MapZoomChangedEvent;
import io.jmix.mapsflowui.component.model.feature.PointFeature;
import io.jmix.mapsflowui.component.model.layer.VectorLayer;
import io.jmix.mapsflowui.component.model.source.ClusterSource;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.feature.Feature;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.CircleStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "geo-map-cluster", layout = MainView.class)
@ViewController(id = "GeoMapCluster")
@ViewDescriptor(path = "geo-map-cluster.xml")
public class GeoMapCluster extends StandardView {

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Notifications notifications;

    @ViewComponent
    private GeoMap map;

    @ViewComponent("map.provinceLayer.provinceCluster")
    private ClusterSource provinceCluster;

    @ViewComponent("map.districtLayer.districtCluster")
    private ClusterSource districtCluster;

    @ViewComponent("map.communeLayer.communeCluster")
    private ClusterSource communeCluster;

    @ViewComponent("map.provinceLayer.provinceSource")
    private VectorSource provinceSource;

    @ViewComponent("map.districtLayer.districtSource")
    private VectorSource districtSource;

    @ViewComponent("map.provinceLayer")
    private VectorLayer provinceLayer;

    @ViewComponent("map.districtLayer")
    private VectorLayer districtLayer;

    @ViewComponent("map.communeLayer")
    private VectorLayer communeLayer;

    @ViewComponent("map.communeLayer.communeCluster.communeSource")
    private DataVectorSource<Commune> communeSource;

    @ViewComponent
    private CollectionContainer<Commune> communesDc;

    @Subscribe
    public void onInit(final InitEvent event) {
        // Tổng hợp dữ liệu cho cấp TỈNH
        List<KeyValueEntity> provinceAggregates = dataManager.loadValues(
                        "select p.name as province, sum(c.propertyCount) as totalProperty, p.coordinates as coordinates " +
                                "from Province p join p.districts d join d.communes c " +
                                "where p.name is not null group by p.name, p.coordinates")
                .properties("province", "totalProperty", "coordinates")
                .list();

        List<Feature> provinceFeatures = new ArrayList<>();
        for (KeyValueEntity entity : provinceAggregates) {
            Long totalProperty = entity.getValue("totalProperty");
            Style featureStyle = new Style()
                    .withImage(new CircleStyle()
                            .withRadius(14) // Same radius as the cluster
                            .withFill(new Fill("#FF3333"))
                            .withStroke(new Stroke()
                                    .withWidth(4.0)
                                    .withColor("#FF9999"))) // Same color as the cluster
                    .withText(new TextStyle()
                            .withFont("10px sans-serif")
                            .withFill(new Fill("#FFFFFF"))
                            .withText(totalProperty != null ? totalProperty.toString() : ""));

            Point coordinates = entity.getValue("coordinates");
            provinceFeatures.add(
                    new PointFeature(coordinates)
                            .withStyles(featureStyle)); // Áp dụng kiểu cho từng điểm
        }
        provinceSource.addAllFeatures(provinceFeatures);

        // Tổng hợp dữ liệu cho cấp HUYỆN
        List<KeyValueEntity> districtAggregates = dataManager.loadValues(
                        "select d.province.name as province, d.name as district, sum(c.propertyCount) as totalProperty, d.coordinates as coordinates " +
                                "from District d join d.communes c " +
                                "where d.name is not null group by d.province.name, d.name, d.coordinates")
                .properties("province", "district", "totalProperty", "coordinates")
                .list();

        List<Feature> districtFeatures = new ArrayList<>();
        for (KeyValueEntity entity : districtAggregates) {
            Long totalProperty = entity.getValue("totalProperty");
            Style featureStyle = new Style()
                    .withImage(new CircleStyle()
                            .withRadius(15)
                            .withFill(new Fill("#9933FF"))
                            .withStroke(new Stroke()
                                    .withWidth(4.0)
                                    .withColor("#CC99FF")))
                    .withText(new TextStyle()
                            .withFont("12px sans-serif")
                            .withFill(new Fill("#FFFFFF"))
                            .withText(totalProperty != null ? totalProperty.toString() : ""));


            Point coordinates = entity.getValue("coordinates");
            districtFeatures.add(
                    new PointFeature(coordinates)
                            .withStyles(featureStyle)); // Áp dụng kiểu cho từng điểm
        }
        districtSource.addAllFeatures(districtFeatures);
        provinceLayer.setVisible(true);
        districtLayer.setVisible(false);
        communeLayer.setVisible(false);
    }

    @Subscribe("map")
    public void onMapMapZoomChanged(MapZoomChangedEvent event) {
        double zoom = event.getZoom();
        provinceLayer.setVisible(zoom > 4 && zoom <= 11);
        districtLayer.setVisible(zoom >11  && zoom <= 14);
        communeLayer.setVisible(zoom > 14);
    }
}