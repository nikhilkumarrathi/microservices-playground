import * as React from 'react';
import { Chart } from 'react-charts';

interface ChartState { 
  axes: any,
  data: [],
  keys: [string,boolean][],
  viewData: {}
}

export class MyChart extends React.Component<{}, ChartState> {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
      viewData: {},
      keys: [],
      axes: [
        { primary: true, type: 'time', position: 'bottom' },
        { type: 'linear', position: 'left' }
      ],
    }
    
    const getData = () => { 
      fetch("/analysis/views").then(response => response.json()).then(d => {
        console.log(d)
        let keys = d.map(o => [o.label, true]);
        let visibleKeys = keys.filter(keyItem => keyItem[1]).map(keyItem => keyItem[0])
        let viewData = d.filter(o => visibleKeys.indexOf(o.label) != -1);
        this.setState({ data: d, viewData : viewData , keys: keys});
      });
    }
    
    getData();
    setInterval(() => getData(), 30000);

    this.toggleKeys = this.toggleKeys.bind(this);
  }

  toggleKeys(k: string){ 
    let keys = this.state.keys.map(keyItem => { 
      if (keyItem[0] == k) {
        return [keyItem[0], !keyItem[1]];
      }
      return keyItem;
    });

   let visibleKeys = keys.filter(keyItem => keyItem[1]).map(keyItem => keyItem[0])
   let viewData = this.state.data.filter(o => visibleKeys.indexOf(o.label) != -1);
   this.setState({viewData : viewData , keys: keys})
  }



  render() { 
    return (
      <>
        <div className="row mt-5">
          <div className="col-8">
                <div
              style={{
                  width: '100%',
                  height: '400px'
              }}
              >
              <Chart data={this.state.viewData} axes={this.state.axes} />
            </div>
          </div>
        <div className="col-4">
          <ul className="list-group list-group-horizontal">
            {
              this.state.keys.map(k => (<li className="list-group-item"><span onClick={() => this.toggleKeys(k[0])} className={k[1]? 'on' : 'off'}>{k[0]}</span></li>))
            }
          </ul>
          </div>
          </div>
      </>
    )
  }
}
