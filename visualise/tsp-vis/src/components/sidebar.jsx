import React from 'react';
import './sidebar.css';
import Heading from 'arui-feather/heading';
import Label from 'arui-feather/label';
import Button from '@mui/material/Button';
import { FormControl,InputLabel,Select, MenuItem,ThemeProvider,createTheme,Typography } from '@mui/material';
function Sidebar({handleRender,prop}) {
    const [algo, setAlgo] = React.useState('');

    const handleSelectChange = async (event) => {
        setAlgo(event.target.value);
        await handleRender(event.target.value, false);
      };

      const darkTheme = createTheme({
        palette: {
          mode: 'dark',
        },
      });

  return (


    <div >
            <ThemeProvider theme={darkTheme}>
        <div className="Sidebar">
        <div className="Sidebar__content">
        <span>&nbsp;&nbsp;</span>
        <span>&nbsp;&nbsp;</span>
        <div>
        <Typography variant="h5" gutterBottom theme={darkTheme}>
            Travelling Salesman Problem
         </Typography> 
        </div>
  
          <br/>
          <div>
          Select Algorithm:        
          </div>
          <span>&nbsp;&nbsp;</span>
          <div>
          <FormControl className="Sidebar__Input" fullWidth >
                <InputLabel id="demo-simple-select-label">Algorithm</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={algo}
                    label="Algorithm"
                    onChange={handleSelectChange}
                    >
                    <MenuItem value={"christofides"}>Christofides Algorithm</MenuItem>
                    <MenuItem value={"annealing"}>Simulated Annealing</MenuItem>
                    {/* <MenuItem value={"algo3"}>Algo3</MenuItem> */}
                    <MenuItem value={""}>NONE</MenuItem>
                </Select>
            </FormControl>
          </div>

          {/* <Input
            className="Sidebar__Input"
            type="number"
            placeholder="100"
            view="line"
            pattern="[0-9]{3}"
            maxlength="3"
            title="Numbers only"
            //onChange={this.handleInputChange}
            required
          /> */}
          <div className="Sidebar__Group">
            <Button
              variant="contained"
              className="Sidebar__Button"
              //disabled={tempIsEmpty}
              onClick={()=>handleRender(algo, true)}
            >
              Generate
            </Button>
          </div>
          <div>
        <Typography variant="h5" gutterBottom theme={darkTheme}>
            Total Distance: {prop.weight}
         </Typography> 
        </div>


        </div>
      </div>
      </ThemeProvider>
    </div>


  );
}

export default Sidebar;