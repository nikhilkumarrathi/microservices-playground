import * as React from "react";
import * as ReactDOM from "react-dom";
import { useState, useEffect } from "react";
import products, { Product } from '../data/products';


export class ProductComponent extends React.Component<{}, {}> {
  
  constructor(props) {
    super(props);
    this.handleClick = this.handleClick.bind(this);
    this.productCard = this.productCard.bind(this);

    setInterval(() => {
      let randomIndex = Math.floor(Math.random() * products.length);
      this.handleClick(products[randomIndex]);
    }, 5000);    

  }

  componentWillUnmount() { 
    // clearInterval(this.state.intervalId)
  }

  handleClick(product: Product) {
      fetch("/products/view", {
        method: 'POST',
        cache: 'no-cache',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(product) 
      });
  }
  
  productCard(product: Product) { 
    return (
      <div className="col-lg-3 col-6" key={product.id}>
        <div className="card">
          <img className="card-img-top" src={product.url} alt="Card image cap"/>
          <div className="card-body">
            <h5 className="card-title">{product.name}</h5>
            <button className="btn btn-primary" onClick={() => this.handleClick(product)}> Choose </button>
          </div>
        </div>
    </div>
    );
  }

  render() {
    return (
      <div className="row mt-3">
        { products.map(p => (this.productCard(p))) }
      </div>
    );
  }
}
