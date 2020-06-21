import * as React from "react";
import * as ReactDOM from "react-dom";
import { MyChart, MyApexChart } from "./components/MyChart";
import { StructureComponent } from "./components/StructureComponent";

ReactDOM.render(
  <>
    <StructureComponent appName="Analytics App" fluid={true}>
      {/* <MyChart /> */}
      <MyApexChart/>
    </StructureComponent>
  </>,
  document.getElementById("root")
);
