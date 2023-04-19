import React from 'react';
import './App.css';
import Sidebar from './components/sidebar';
import Canva from './components/canva';
function App() {
  const [algorithm, setAlgorithm] = React.useState('');
  const [isRender, setIsRender] = React.useState(false);
  const [weight, setWeight] = React.useState(0);
  const handleRender = async (algo, isRender) => {
      await setAlgorithm(algo);
      await setIsRender(isRender);

      console.log(algorithm,isRender);
    };
    const getWeight = async (val) => {
      await setWeight(val);
    };

  return (
    <div className="App">
      <div className='SideBar'>
        <Sidebar handleRender = {handleRender} prop = {{ weight: weight }}/>
      </div>
      <div className='Canvas'>
        {/* <div>
          <Test />
        </div> */}
        <div>
          <Canva getWeight={getWeight} props={{algorithm: algorithm, render: isRender}}/>
        </div>
      </div>
    </div>
  );
}

export default App;