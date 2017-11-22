/* Configure Map */

// To configure center, zoom level and projection of the map
var view = new ol.View({
    zoom: 9
});

// Renders the map on the target div
var map = new ol.Map({
    target: "map",
    layers: [
        new ol.layer.Tile({source: new ol.source.OSM()})
    ],
    controls: ol.control.defaults({
        attributionOptions: ({
            collapsible: false
        })
    }), view: view
});

/* Location  */

var geolocation = new ol.Geolocation({
    projection: view.getProjection()
});
geolocation.on("error", function (error) {
    alert(error.message);
});
// Set styling
var positionFeature = new ol.Feature();
positionFeature.setStyle(new ol.style.Style({
    image: new ol.style.Icon({src: "user.png", scale: 0.5})
}));
// On location change
var centerDefined = false;
geolocation.on("change:position", function () {
    var coordinates = geolocation.getPosition();
    if (!centerDefined) {
        view.setCenter(coordinates);
        centerDefined = true;
    }
    positionFeature.setGeometry(coordinates ? new ol.geom.Point(coordinates) : null);
});
new ol.layer.Vector({
    map: map, source: new ol.source.Vector({
        features: [positionFeature]
    })
});
geolocation.setTracking(true);

/* Message Box */

var element = document.getElementById('message-box');

// Show message box on top of map
var popup = new ol.Overlay({
    element: element,
    positioning: 'bottom-center'
});
map.addOverlay(popup);

// Open the message box on clicking on map
map.on('click', function (evt) {
    var coordinate = evt.coordinate;
    var feature = map.forEachFeatureAtPixel(evt.pixel,
        function (feature) {
            return feature;
        });

    $(element).popover('destroy');
    if (feature) {
        popup.setPosition(coordinate);
        $(element).popover({
            'placement': 'top',
            'html': true,
            'content': feature.get('content'),
            'animation': false
        });
        $(element).popover('show');
    } else {
        popup.setPosition(coordinate);
        $(element).popover({
            'placement': 'top',
            'html': true,
            'title': "New message",
            'animation': false

        }).data('bs.popover').tip().width(250).height(300).append("<div id='message' style='height:70%'/>");
        $(element).popover('show');
        $("#message").editable(function (value, settings) {
            $.ajax({
                method: "POST",
                url: "/message",
                data: JSON.stringify({
                    content: value,
                    location: {
                        type: "Point",
                        coordinates: [coordinate[0], coordinate[1]]
                    }
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            });
            popup.setPosition(undefined);
            return value;
        }, {
            type: "textarea",
            submit: "Save"
        });
    }
});

/* Show messages on the Map */

var vectorSource = new ol.source.Vector({
    loader: function (extent, resolution, projection) {
        var url = '/message/bbox/' + extent[0] + "," + extent[1] + "," + extent[2] + "," + extent[3];
        // Get all the messages from the server based upon the extent
        $.ajax({
            url: url,
            dataType: 'json',
            success: function (response) {
                if (response.error) {
                    alert(response.error.message + '\n' +
                        response.error.details.join('\n'));
                } else {
                    // Plot message icon on the map
                    $.each(response, function (index, value) {
                        var feature = new ol.Feature({
                            geometry: new ol.geom.Point(value.location.coordinates),
                            content: value.content
                        });
                        vectorSource.addFeature(feature);
                    });
                }
            }
        });
    },
    strategy: ol.loadingstrategy.tile(ol.tilegrid.createXYZ({
        tileSize: 512
    }))
});

var vector = new ol.layer.Vector({
    source: vectorSource,
    style: new ol.style.Style({image: new ol.style.Icon({src: "message-box.png", scale: 0.5})})
});

map.addLayer(vector);

/* Reactive: Event listener to get updates when new message is saved */

var source = new EventSource("/message/subscribe");

source.addEventListener('message', function (e) {
    var message = $.parseJSON(e.data);
    var feature = new ol.Feature({
        geometry: new ol.geom.Point(message.location.coordinates),
        content: message.content
    });
    vectorSource.addFeature(feature);
}, false);