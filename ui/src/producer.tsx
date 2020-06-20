import * as React from "react";
import * as ReactDOM from "react-dom";
import {ProductComponent} from "./components/ProductComponent";
import products from './data/products';
import { StructureComponent } from "./components/StructureComponent";

ReactDOM.render(
  <>
    <StructureComponent appName="Producer App" fluid={false}>
      <div className="row mt-3">
          <div className="col-12">
          <div className="card">
            <div className="card-body">
            This Page will generate one Random Product event every 5 seconds
            </div>
          </div>
        </div>
      </div>
      <ProductComponent/>
    </StructureComponent>
    </>,
    document.getElementById("root")
);