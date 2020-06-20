import * as React from "react";
import * as ReactDOM from "react-dom";
import { MyChart } from "./components/MyChart";
import { StructureComponent } from "./components/StructureComponent";

ReactDOM.render(
  <>
    <StructureComponent appName="Analytics App" fluid={true}>
      <MyChart />
    </StructureComponent>
  </>,
  document.getElementById("root")
);
