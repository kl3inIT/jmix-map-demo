package com.company.maps.view.geomapcluster;

import com.company.maps.entity.Location;
import com.company.maps.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.model.feature.PointFeature;
import io.jmix.mapsflowui.component.model.source.ClusterSource;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.feature.Feature;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.CircleStyle;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "geo-map-cluster", layout = MainView.class)
@ViewController(id = "GeoMapCluster")
@ViewDescriptor(path = "geo-map-cluster.xml")
public class GeoMapCluster extends StandardView {

//    @Autowired
//    private DataManager dataManager;
//
//    @Autowired
//    private Notifications notifications;
//
//    @ViewComponent
//    private GeoMap map;
//
//    @ViewComponent("map.provinceLayer.provinceCluster")
//    private ClusterSource provinceCluster;
//
//    @ViewComponent("map.districtLayer.districtCluster")
//    private ClusterSource districtCluster;
//
//    @ViewComponent("map.communeLayer.communeCluster")
//    private ClusterSource communeCluster;
//
//    @ViewComponent("map.provinceLayer.provinceCluster.provinceSource")
//    private VectorSource provinceSource;
//
//    @ViewComponent("map.districtLayer.districtCluster.districtSource")
//    private VectorSource districtSource;
//
//    @ViewComponent("map.communeLayer.communeCluster.communeSource")
//    private DataVectorSource<Location> communeSource;
//
//    @ViewComponent
//    private CollectionContainer<Location> locationsDc;
//
//    @Subscribe
//    public void onInit(final InitEvent event) {
//        List<KeyValueEntity> provinceAggregates = dataManager.loadValues(
//                        "select e.province, sum(e.propertyCount) as totalProperty, " +
//                                "avg(function('ST_X', function('ST_GeomFromText', e.coordinates, 4326))) as avgX, " +
//                                "avg(function('ST_Y', function('ST_GeomFromText', e.coordinates, 4326))) as avgY " +
//                                "from Location e where e.province is not null group by e.province")
//                .properties("province", "totalProperty", "avgX", "avgY")
//                .list();
//
//        List<Feature> provinceFeatures = new ArrayList<>();
//        for (KeyValueEntity entity : provinceAggregates) {
//            String province = entity.getValue("province");
//            Integer totalProperty = entity.getValue("totalProperty");
//            Double avgX = entity.getValue("avgX");
//            Double avgY = entity.getValue("avgY");
//            Point point = GeometryUtils.createPoint(avgX, avgY);
//            provinceFeatures.add(
//                    new PointFeature(point)
//                            .withProperty("province", province)
//                            .withProperty("propertyCount", totalProperty));
//        }
//        provinceSource.addAllFeatures(provinceFeatures);
//
//        List<KeyValueEntity> districtAggregates = dataManager.loadValues(
//                        "select e.province, e.district, sum(e.propertyCount) as totalProperty, " +
//                                "avg(function('ST_X', function('ST_GeomFromText', e.coordinates, 4326))) as avgX, " +
//                                "avg(function('ST_Y', function('ST_GeomFromText', e.coordinates, 4326))) as avgY " +
//                                "from Location e where e.district is not null group by e.province, e.district")
//                .properties("province", "district", "totalProperty", "avgX", "avgY")
//                .list();
//
//        List<Feature> districtFeatures = new ArrayList<>();
//        for (KeyValueEntity entity : districtAggregates) {
//            String province = entity.getValue("province");
//            String district = entity.getValue("district");
//            Integer totalProperty = entity.getValue("totalProperty");
//            Double avgX = entity.getValue("avgX");
//            Double avgY = entity.getValue("avgY");
//            Point point = GeometryUtils.createPoint(avgX, avgY);
//            districtFeatures.add(
//                    new PointFeature(point)
//                            .withProperty("province", province)
//                            .withProperty("district", district)
//                            .withProperty("propertyCount", totalProperty));
//        }
//        districtSource.addAllFeatures(districtFeatures);
//
//        // Tùy chỉnh kiểu cho cụm TỈNH
//        provinceCluster.addPointStyles(new Style()
//                .withImage(new CircleStyle()
//                        .withRadius(20)
//                        .withFill(new Fill("#FF5733")))); // Màu đỏ cam
//        provinceCluster.setPointTextStyle(new Style()
//                .withText(new TextStyle()
//                        .withFont("16px sans-serif")
//                        .withFill(new Fill("#FFFFFF"))));
//
//        // Tùy chỉnh kiểu cho cụm HUYỆN
//        districtCluster.addPointStyles(new Style()
//                .withImage(new CircleStyle()
//                        .withRadius(15)
//                        .withFill(new Fill("#33C4FF")))); // Màu xanh dương
//        districtCluster.setPointTextStyle(new Style()
//                .withText(new TextStyle()
//                        .withFont("14px sans-serif")
//                        .withFill(new Fill("#FFFFFF"))));
//
//        // Tùy chỉnh kiểu cho cụm XÃ
//        communeCluster.addPointStyles(new Style()
//                .withImage(new CircleStyle()
//                        .withRadius(10)
//                        .withFill(new Fill("#33FF57")))); // Màu xanh lá
//        communeCluster.setPointTextStyle(new Style()
//                .withText(new TextStyle()
//                        .withFont("12px sans-serif")
//                        .withFill(new Fill("#FFFFFF"))));

//        // Thêm listener cho nhấp chuột vào cụm
//        provinceSource.addGeoObjectClickListener(clickEvent -> {
//            String province = (String) clickEvent.getFeature().getProperty("province");
//            Integer propertyCount = (Integer) clickEvent.getFeature().getProperty("propertyCount");
//            notifications.show("Province: " + province + ", Property Count: " + propertyCount);
//            map.fit(new FitOptions(clickEvent.getFeature().getGeometry()).withMaxZoom(8d));
//        });
//
//        districtSource.addGeoObjectClickListener(clickEvent -> {
//            String district = (String) clickEvent.getFeature().getProperty("district");
//            Integer propertyCount = (Integer) clickEvent.getFeature().getProperty("propertyCount");
//            notifications.show("District: " + district + ", Property Count: " + propertyCount);
//            map.fit(new FitOptions(clickEvent.getFeature().getGeometry()).withMaxZoom(12d));
//        });
//
//        communeSource.addGeoObjectClickListener(clickEvent -> {
//            Location location = clickEvent.getItem();
//            notifications.show("Commune: " + location.getCommune() + ", Property Count: " + location.getPropertyCount());
//            map.fit(new FitOptions(location.getCoordinates()).withMaxZoom(18d));
//        });
//    }
}