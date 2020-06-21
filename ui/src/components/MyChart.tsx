import * as React from 'react';
import Chart from "react-apexcharts";

export class MyApexChart extends React.Component<{}, {}> {
  constructor(props) {
    super(props);

    const getData = () => { 
      fetch("/analysis/views").then(response => response.json()).then(d => {
        console.log(d)
        this.setState({ series: d});
      });
    }
    this.state = {
      series: [],
      options: {
        chart: {
          height: 500,
          type: 'line',
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: true
        },
        stroke: {
          curve: 'smooth'
        },
        title: {
          text: 'Products Viewed',
          align: 'left'
        },
        grid: {
          row: {
            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
            opacity: 0.5
          },
        },
        xaxis: {
          categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'],
        }
      },

    };

    getData();
  }
  render() {
    return (
      <div id="chart">
        <Chart options={this.state.options} series={this.state.series} type="line" height={500} />
      </div>
    );
  }
}