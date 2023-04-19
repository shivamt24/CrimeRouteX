import React from 'react';
import {createRoot} from 'react-dom/client';
import DeckGL, {GeoJsonLayer, ArcLayer, LineLayer} from 'deck.gl';
import {StaticMap, MapContext, NavigationControl} from 'react-map-gl';
// const fs = require("fs");
// const path = require("path");

// source: Natural Earth http://www.naturalearthdata.com/ via geojson.xyz
const COUNTRIES ='https://d2ad6b4ur7yvpq.cloudfront.net/naturalearth-3.3.0/ne_50m_admin_0_scale_rank.geojson'; //eslint-disable-line



const INITIAL_VIEW_STATE = {
  latitude: 51.483548,
  longitude: -0.009691,
  zoom: 9,
  bearing: 0,
  pitch: 30
};
const MAP_STYLE = 'https://basemaps.cartocdn.com/gl/dark-matter-gl-style/style.json';
//const MAP_STYLE = "https://basemaps.cartocdn.com/gl/dark-matter-nolabels-gl-style/style.json"
const NAV_CONTROL_STYLE = {
  position: 'absolute',
  top: 10,
  left: 10
};

function Canva({getWeight,props}) {
    
  //const dataSet = fs.readFileSync(path.join(__dirname, `../geoJson/algo1`));
  const dataset =  import (`../geoJson/${props.algorithm}.json`);
  const onClick = info => {
    if (info.object) {
      // eslint-disable-next-line
      alert(`Id: ${info.object.properties.id}`);
    }
  };
  const [val, setVal] = React.useState(0);
  React.useEffect( () => {
        // Update the document title using the browser API
        dataset.then(d=>{
          setVal(d.features[0].properties.distance);
          console.log(d.features[0].properties.distance);
        })
        getWeight(val);

      });


  const layers = [
    new GeoJsonLayer({
      id: 'location',
      data: dataset,
      // Styles
      filled: true,
      pointRadiusMinPixels: 6,
      pointRadiusScale: 8,
      //getPointRadius: f => 11 - f.properties.scalerank,
      getFillColor: [200, 0, 80, 180],
      // Interactive props
      pickable: true,
      autoHighlight: true,
      onClick
    })
  ];
  if(props.render){
    layers.push(
        new LineLayer({
            id: 'lines',
            data: dataset,
            dataTransform: d => d.features.filter(f => true),
            // Styles
            getSourcePosition: f => f.geometry.coordinates, // London
            getTargetPosition: f => ([parseFloat(f.properties.tolongitude) , parseFloat(f.properties.tolatitude)]),
            getColor: [255,255,255],
            getWidth: 1
          })
    )
  }
  return (
    <div>
    <DeckGL
      initialViewState={INITIAL_VIEW_STATE}
      controller={true}
      layers={layers}
      ContextProvider={MapContext.Provider}
      style={{ height: '100vh', width: '100vw', position: 'relative' }}
    >
      <StaticMap mapStyle={MAP_STYLE} />
      <NavigationControl style={NAV_CONTROL_STYLE} />
    </DeckGL>
    </div>

  );
}

export default Canva;