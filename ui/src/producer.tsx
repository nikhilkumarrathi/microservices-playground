import * as React from "react";
import * as ReactDOM from "react-dom";
import {ProductComponent} from "./components/ProductComponent";
import products from './data/products';
import { StructureComponent } from "./components/StructureComponent";

ReactDOM.render(
  <>
    <StructureComponent appName="Producer App" fluid={false}>
      <ProductComponent generateRandom={false}/>
    </StructureComponent>
    </>,
    document.getElementById("root")
);